"""
Minimal EHLLAPI wrapper for Windows mainframe (3270) emulators using ctypes.

Works with emulators that export a standard `hllapi` entry point, e.g.:
  - IBM Personal Communications : pcshll32.dll  (64-bit: pcshll64.dll?) entry "hllapi"
  - Generic EHLLAPI             : ehlapi32.dll  entry "hllapi"
  - WinHLLAPI-style             : whllapi.dll   entry "WinHLLAPI"
Check your DLL's exports and adjust DLL_PATH / ENTRY below.

Python bitness (32/64-bit) MUST match the DLL.
The emulator session must already be open (e.g. session short name "A").
"""

import ctypes
import time


class HLLAPIError(Exception):
    pass


class HLLAPI:
    # Standard EHLLAPI function numbers
    CONNECT = 1
    DISCONNECT = 2
    SEND_KEY = 3
    WAIT = 4
    COPY_PS = 5
    SEARCH_PS = 6
    QUERY_CURSOR = 7
    COPY_PS_TO_STR = 8
    SET_PARAMS = 9
    COPY_STR_TO_PS = 15
    RESET = 21
    FIND_FIELD_POS = 31
    FIND_FIELD_LEN = 32
    SET_CURSOR = 40

    def __init__(self, dll_path, entry="hllapi", rows=24, cols=80):
        self.rows, self.cols = rows, cols
        dll = ctypes.WinDLL(dll_path)  # stdcall; try ctypes.CDLL if calls crash
        self._fn = getattr(dll, entry)
        self._fn.argtypes = [
            ctypes.POINTER(ctypes.c_int),  # function number
            ctypes.c_char_p,               # data string
            ctypes.POINTER(ctypes.c_int),  # length
            ctypes.POINTER(ctypes.c_int),  # return code / PS position
        ]
        self._fn.restype = None

    # ---- low-level call -------------------------------------------------
    def call(self, func, data=b"", length=None, rc_in=0, buf_size=None):
        if isinstance(data, str):
            data = data.encode("latin-1")
        size = max(buf_size or 0, len(data), length or 0) + 1
        buf = ctypes.create_string_buffer(data, size)
        f = ctypes.c_int(func)
        ln = ctypes.c_int(length if length is not None else len(data))
        rc = ctypes.c_int(rc_in)
        self._fn(ctypes.byref(f), buf, ctypes.byref(ln), ctypes.byref(rc))
        return rc.value, ln.value, buf.raw[:size - 1]

    def _check(self, rc, what, ok=(0,)):
        if rc not in ok:
            raise HLLAPIError(f"{what} failed, rc={rc}")

    def pos(self, row, col):
        """1-based row/col -> 1-based linear presentation-space position."""
        return (row - 1) * self.cols + col

    # ---- session --------------------------------------------------------
    def connect(self, session="A"):
        rc, _, _ = self.call(self.CONNECT, session)
        self._check(rc, "Connect")

    def disconnect(self):
        self.call(self.DISCONNECT)

    def reset(self):
        self.call(self.RESET)

    # ---- input ----------------------------------------------------------
    def send_keys(self, keys):
        """Send keystrokes. Mnemonics: @E Enter, @T Tab, @C Clear,
        @1..@9 PF1-9, @a..@o PF10-24, @R Reset. Literal '@' -> '@@'."""
        rc, _, _ = self.call(self.SEND_KEY, keys)
        self._check(rc, "SendKey")

    def type_text(self, text):
        self.send_keys(text.replace("@", "@@"))

    def enter(self):
        self.send_keys("@E")

    def pf(self, n):
        codes = "123456789abcdefghijklmno"
        self.send_keys("@" + codes[n - 1])

    def set_cursor(self, row, col):
        rc, _, _ = self.call(self.SET_CURSOR, rc_in=self.pos(row, col))
        self._check(rc, "SetCursor")

    def write_at(self, row, col, text):
        """Write text into an unprotected field at row/col."""
        rc, _, _ = self.call(self.COPY_STR_TO_PS, text, rc_in=self.pos(row, col))
        self._check(rc, "CopyStringToPS")

    # ---- dynamic waits --------------------------------------------------
    # All waits poll until a condition is true and return as soon as it is,
    # or raise HLLAPIError after `timeout` seconds. No fixed sleeps needed.

    def wait_until(self, condition, timeout=30, poll=0.25, what="condition"):
        """Generic wait: poll condition() until it returns something truthy."""
        end = time.time() + timeout
        while True:
            try:
                result = condition()
                if result:
                    return result
            except HLLAPIError:
                pass  # screen mid-update; try again
            if time.time() >= end:
                raise HLLAPIError(f"Timed out after {timeout}s waiting for {what}")
            time.sleep(poll)

    def wait_ready(self, timeout=30, poll=0.25):
        """Wait until the host unlocks the keyboard (rc 0)."""
        def ready():
            rc, _, _ = self.call(self.WAIT)
            return rc == 0  # rc 4 = X SYSTEM busy, 5 = keyboard locked
        return self.wait_until(ready, timeout, poll, "host ready")

    def _on_screen(self, text, ignore_case=True):
        scr = "".join(self.screen())
        return (text.upper() in scr.upper()) if ignore_case else (text in scr)

    def wait_for_text(self, text, timeout=30, poll=0.25):
        """Wait until text appears anywhere on screen."""
        return self.wait_until(lambda: self._on_screen(text), timeout, poll,
                               f"text {text!r}")

    def wait_for_text_gone(self, text, timeout=30, poll=0.25):
        """Wait until text disappears, e.g. 'PROCESSING' or 'PLEASE WAIT'."""
        return self.wait_until(lambda: not self._on_screen(text), timeout, poll,
                               f"text {text!r} to disappear")

    def wait_for_any(self, *texts, timeout=30, poll=0.25):
        """Wait for whichever text appears first and return it.
        Useful for branching: success screen vs error message."""
        def any_found():
            scr = "".join(self.screen()).upper()
            for t in texts:
                if t.upper() in scr:
                    return t
            return None
        return self.wait_until(any_found, timeout, poll, f"any of {texts!r}")

    def wait_for_text_at(self, row, col, text, timeout=30, poll=0.25):
        """Wait until exact text is at a given position (e.g. screen title)."""
        return self.wait_until(
            lambda: self.read(row, col, len(text)).upper() == text.upper(),
            timeout, poll, f"{text!r} at ({row},{col})")

    def wait_for_field(self, label, timeout=30, poll=0.25):
        """Wait until a label AND its input field are present."""
        return self.wait_until(lambda: self.field_after(label), timeout, poll,
                               f"field {label!r}")

    def wait_for_stable(self, stable_for=1.0, timeout=30, poll=0.25):
        """Wait until the screen stops changing for `stable_for` seconds.
        Handles hosts that unlock the keyboard before painting finishes."""
        state = {"last": None, "since": time.time()}
        def stable():
            scr = self.screen()
            now = time.time()
            if scr != state["last"]:
                state["last"], state["since"] = scr, now
                return False
            return now - state["since"] >= stable_for
        return self.wait_until(stable, timeout, poll, "screen to stabilise")

    def wait_for_screen(self, timeout=30, stable_for=0.5):
        """Best default after pressing Enter/PF: host ready + screen settled."""
        self.wait_ready(timeout)
        self.wait_for_stable(stable_for, timeout)

    # ---- AID keys that wait automatically --------------------------------
    def submit(self, key="@E", expect=None, timeout=30):
        """Press Enter (or any AID key) and wait for the next screen.
        If expect is given (str or tuple of str), also wait for that text
        and return which one appeared."""
        self.send_keys(key)
        self.wait_for_screen(timeout)
        if expect is None:
            return None
        if isinstance(expect, str):
            expect = (expect,)
        return self.wait_for_any(*expect, timeout=timeout)

    # ---- reading --------------------------------------------------------
    def screen(self):
        """Whole screen as a list of strings (one per row)."""
        n = self.rows * self.cols
        rc, _, raw = self.call(self.COPY_PS, length=n, buf_size=n)
        self._check(rc, "CopyPS")
        text = raw[:n].decode("latin-1")
        return [text[i:i + self.cols] for i in range(0, n, self.cols)]

    def read(self, row, col, length):
        rc, _, raw = self.call(self.COPY_PS_TO_STR, length=length,
                               rc_in=self.pos(row, col), buf_size=length)
        self._check(rc, "CopyPSToString")
        return raw[:length].decode("latin-1")

    def find(self, text):
        """Return (row, col) of text on screen, or None."""
        rc, ln, _ = self.call(self.SEARCH_PS, text, rc_in=1)
        if rc != 0 or ln == 0:
            return None
        return ((ln - 1) // self.cols + 1, (ln - 1) % self.cols + 1)

    def cursor(self):
        _, ln, _ = self.call(self.QUERY_CURSOR)
        return ((ln - 1) // self.cols + 1, (ln - 1) % self.cols + 1)

    # ---- label-based field locators -------------------------------------
    def find_label(self, label, occurrence=1, ignore_case=True):
        """Find the Nth occurrence of a label on screen.
        Returns 1-based linear position of the label's LAST character."""
        text = "".join(self.screen())
        hay, needle = (text.upper(), label.upper()) if ignore_case else (text, label)
        idx = -1
        for _ in range(occurrence):
            idx = hay.find(needle, idx + 1)
            if idx < 0:
                raise HLLAPIError(f"Label {label!r} (occurrence {occurrence}) not on screen")
        return idx + len(needle)  # 0-based index of last char + 1 = 1-based pos

    def field_after(self, label, occurrence=1):
        """Position and length of the first input (unprotected) field
        following a label, e.g. 'USERID ===>' then the blank input field."""
        start = self.find_label(label, occurrence)
        rc, fpos, _ = self.call(self.FIND_FIELD_POS, "NU", rc_in=start)
        self._check(rc, f"FindFieldPosition after {label!r}")
        rc, flen, _ = self.call(self.FIND_FIELD_LEN, "T ", rc_in=fpos)
        self._check(rc, f"FindFieldLength after {label!r}")
        return fpos, flen

    def fill(self, label, text, occurrence=1, clear=True):
        """Type text into the input field next to a label.
        fill("USERID", "MYUSER"); fill("PASSWORD", "secret")"""
        fpos, flen = self.field_after(label, occurrence)
        if len(text) > flen:
            raise HLLAPIError(f"{label!r} field holds {flen} chars, got {len(text)}")
        rc, _, _ = self.call(self.SET_CURSOR, rc_in=fpos)
        self._check(rc, "SetCursor")
        if clear:
            self.send_keys("@F")  # Erase EOF: blank the field first
        self.type_text(text)

    def read_field(self, label, occurrence=1):
        """Read the current contents of the input field next to a label."""
        fpos, flen = self.field_after(label, occurrence)
        rc, _, raw = self.call(self.COPY_PS_TO_STR, length=flen,
                               rc_in=fpos, buf_size=flen)
        self._check(rc, "CopyPSToString")
        return raw[:flen].decode("latin-1").rstrip()

    def value_after(self, label, length, occurrence=1):
        """Read protected (display-only) text right after a label,
        e.g. value_after('ACCOUNT NO:', 10)."""
        start = self.find_label(label, occurrence) + 1
        rc, _, raw = self.call(self.COPY_PS_TO_STR, length=length,
                               rc_in=start, buf_size=length)
        self._check(rc, "CopyPSToString")
        return raw[:length].decode("latin-1").strip()


# ---- example ------------------------------------------------------------
if __name__ == "__main__":
    DLL_PATH = r"C:\Program Files (x86)\IBM\Personal Communications\pcshll32.dll"
    ENTRY = "hllapi"

    h = HLLAPI(DLL_PATH, ENTRY)
    h.connect("A")
    try:
        h.wait_ready()
        print("\n".join(h.screen()))

        # Example login flow with label locators + dynamic waits
        # h.wait_for_field("USERID")
        # h.fill("USERID", "MYUSER")
        # h.fill("PASSWORD", "MYPASS")
        # result = h.submit(expect=("MAIN MENU", "INVALID PASSWORD"))
        # if result == "INVALID PASSWORD":
        #     raise SystemExit("Login failed")
        # h.submit("@3")                          # PF3, wait for next screen
        # h.wait_for_text_gone("PROCESSING", timeout=120)
        # print(h.read_field("COMMAND"))          # read an input field
        # print(h.value_after("BALANCE:", 12))    # read display-only text
        # h.fill("NAME", "SMITH", occurrence=2)   # 2nd 'NAME' label on screen
    finally:
        h.disconnect()
