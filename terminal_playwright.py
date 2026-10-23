"""
Playwright-style API for mainframe 3270 emulators, built on hllapi.py.

    from terminal_playwright import Terminal, expect

    with Terminal(r"C:\\...\\pcshll32.dll", session="A") as page:
        page.get_by_label("USERID").fill("MYUSER")
        page.get_by_label("PASSWORD").fill("MYPASS")
        page.keyboard.press("Enter")
        expect(page.get_by_text("MAIN MENU")).to_be_visible()

Timeouts are in milliseconds, like Playwright. Actions auto-wait:
fill() waits for the field, AID keys (Enter, F1-F24, PA1-3, Clear) wait for
the next screen to finish drawing.
"""

import time

from hllapi import HLLAPI, HLLAPIError


class TimeoutError(HLLAPIError):
    pass


# ---------------------------------------------------------------------------
# Keyboard
# ---------------------------------------------------------------------------
_PF = "123456789abcdefghijklmno"  # EHLLAPI mnemonics for PF1-PF24


class Keyboard:
    KEYS = {
        "Enter": "@E", "Tab": "@T", "Shift+Tab": "@B", "Backtab": "@B",
        "Clear": "@C", "Reset": "@R", "Home": "@0", "EraseEOF": "@F",
        "Delete": "@D", "Backspace": "@<", "Insert": "@I",
        "ArrowUp": "@U", "ArrowDown": "@V", "ArrowLeft": "@L", "ArrowRight": "@Z",
        "NewLine": "@N", "PA1": "@x", "PA2": "@y", "PA3": "@z",
        **{f"F{i}": "@" + _PF[i - 1] for i in range(1, 25)},
        **{f"PF{i}": "@" + _PF[i - 1] for i in range(1, 25)},
    }
    # Keys that send data to the host and produce a new screen
    AID = {"Enter", "Clear", "PA1", "PA2", "PA3",
           *(f"F{i}" for i in range(1, 25)), *(f"PF{i}" for i in range(1, 25))}

    def __init__(self, page):
        self._page = page

    def press(self, key, timeout=None, no_wait_after=False):
        """Press a named key, e.g. 'Enter', 'F3', 'PA1', 'Tab'.
        AID keys wait for the host to respond unless no_wait_after=True."""
        if key not in self.KEYS:
            raise ValueError(f"Unknown key {key!r}. Known: {sorted(self.KEYS)}")
        h = self._page._h
        h.send_keys(self.KEYS[key])
        if key in self.AID and not no_wait_after:
            h.wait_for_screen(timeout=self._page._secs(timeout))

    def type(self, text):
        """Type text at the current cursor position."""
        self._page._h.type_text(text)


# ---------------------------------------------------------------------------
# Locator
# ---------------------------------------------------------------------------
class Locator:
    """Lazy reference to something on screen. Resolved every time it is used,
    so it stays valid across screen changes (just like Playwright)."""

    def __init__(self, page, kind, value, index=0):
        self._page, self._kind, self._value, self._index = page, kind, value, index

    def __repr__(self):
        return f"<Locator {self._kind}={self._value!r} nth={self._index}>"

    @property
    def _h(self):
        return self._page._h

    def _occ(self):
        return self._index + 1

    # ---- refinement ---------------------------------------------------------
    def nth(self, index):
        return Locator(self._page, self._kind, self._value, index)

    @property
    def first(self):
        return self.nth(0)

    @property
    def last(self):
        return self.nth(max(self.count() - 1, 0))

    # ---- state --------------------------------------------------------------
    def count(self):
        scr = self._page.content().upper()
        needle = self._value.upper()
        n, i = 0, scr.find(needle)
        while i >= 0:
            n, i = n + 1, scr.find(needle, i + 1)
        return n

    def is_visible(self):
        try:
            if self._kind == "label":
                self._h.field_after(self._value, self._occ())  # label + input field
            else:
                self._h.find_label(self._value, self._occ())
            return True
        except HLLAPIError:
            return False

    def is_hidden(self):
        return not self.is_visible()

    def wait_for(self, state="visible", timeout=None):
        """state: 'visible' or 'hidden'."""
        if state not in ("visible", "hidden", "attached", "detached"):
            raise ValueError(f"Unknown state {state!r}")
        want = state in ("visible", "attached")
        self._page._wait(lambda: self.is_visible() == want, timeout,
                         f"{self} to be {state}")
        return self

    def position(self):
        """(row, col) of the input field (label) or of the text (text)."""
        cols = self._h.cols
        if self._kind == "label":
            p, _ = self._h.field_after(self._value, self._occ())
        else:
            p = self._h.find_label(self._value, self._occ()) - len(self._value) + 1
        return ((p - 1) // cols + 1, (p - 1) % cols + 1)

    # ---- actions (auto-waiting) --------------------------------------------
    def _require_label(self, action):
        if self._kind != "label":
            raise HLLAPIError(f"{action}() needs a field: use get_by_label(), not {self}")

    def fill(self, value, timeout=None):
        """Wait for the field, clear it and type value."""
        self._require_label("fill")
        self.wait_for("visible", timeout)
        self._h.fill(self._value, value, occurrence=self._occ(), clear=True)

    def clear(self, timeout=None):
        self._require_label("clear")
        self.wait_for("visible", timeout)
        self.click()
        self._h.send_keys("@F")

    def type(self, text, timeout=None):
        """Type without clearing (appends at the start of the field)."""
        self.click(timeout)
        self._h.type_text(text)

    def press(self, key, timeout=None):
        """Put cursor on this element, then press key (e.g. select a menu row)."""
        self.click(timeout)
        self._page.keyboard.press(key, timeout)

    def click(self, timeout=None):
        """Move the cursor here (the 3270 equivalent of focusing/clicking)."""
        self.wait_for("visible", timeout)
        row, col = self.position()
        self._h.set_cursor(row, col)

    focus = click

    # ---- reading ------------------------------------------------------------
    def input_value(self, timeout=None):
        self._require_label("input_value")
        self.wait_for("visible", timeout)
        return self._h.read_field(self._value, self._occ())

    def text_content(self, timeout=None):
        """Field value for labels; the whole screen row for text locators."""
        if self._kind == "label":
            return self.input_value(timeout)
        self.wait_for("visible", timeout)
        row, _ = self.position()
        return self._page.lines()[row - 1].rstrip()

    inner_text = text_content

    def value_after(self, length, timeout=None):
        """Read display-only text right after the label/text, e.g. a balance."""
        self.wait_for("visible", timeout)
        return self._h.value_after(self._value, length, self._occ())


# ---------------------------------------------------------------------------
# Page-like terminal
# ---------------------------------------------------------------------------
class Terminal:
    def __init__(self, dll_path=None, session="A", entry="hllapi",
                 rows=24, cols=80, default_timeout=30_000, hllapi=None):
        self._h = hllapi or HLLAPI(dll_path, entry, rows, cols)
        self.session = session
        self.keyboard = Keyboard(self)
        self._timeout = default_timeout
        self._connected = False

    # ---- lifecycle ----------------------------------------------------------
    def connect(self):
        self._h.connect(self.session)
        self._connected = True
        return self

    def close(self):
        if self._connected:
            self._h.disconnect()
            self._connected = False

    def __enter__(self):
        return self.connect()

    def __exit__(self, *exc):
        self.close()

    # ---- timeouts -----------------------------------------------------------
    def set_default_timeout(self, ms):
        self._timeout = ms

    def _secs(self, ms):
        return (self._timeout if ms is None else ms) / 1000

    def _wait(self, cond, timeout, what):
        try:
            return self._h.wait_until(cond, self._secs(timeout), 0.25, what)
        except HLLAPIError as e:
            raise TimeoutError(str(e)) from None

    # ---- locators -----------------------------------------------------------
    def get_by_label(self, label):
        """The input field that follows a label on screen."""
        return Locator(self, "label", label)

    def get_by_text(self, text):
        """Any text on screen."""
        return Locator(self, "text", text)

    locator = get_by_text

    # ---- page-level ---------------------------------------------------------
    def content(self):
        return "".join(self._h.screen())

    def lines(self):
        return self._h.screen()

    def screenshot(self, path=None):
        """Text 'screenshot' of the screen, optionally saved to a file."""
        text = "\n".join(self.lines())
        if path:
            with open(path, "w", encoding="utf-8") as f:
                f.write(text)
        return text

    def wait_for_load_state(self, timeout=None):
        """Host ready + screen finished drawing."""
        self._h.wait_for_screen(timeout=self._secs(timeout))

    def wait_for_timeout(self, ms):
        time.sleep(ms / 1000)


# ---------------------------------------------------------------------------
# expect()
# ---------------------------------------------------------------------------
class AssertionError_(AssertionError):
    pass


class _LocatorAssertions:
    def __init__(self, loc, negate=False):
        self._loc, self._neg = loc, negate

    @property
    def not_(self):
        return _LocatorAssertions(self._loc, not self._neg)

    def _check(self, cond, what, timeout):
        page = self._loc._page
        target = (lambda: not cond()) if self._neg else cond
        try:
            page._wait(target, timeout, what)
        except TimeoutError:
            raise AssertionError_(
                f"expect({self._loc}){'.not_' if self._neg else ''}.{what} "
                f"failed\n--- screen ---\n{page.screenshot()}") from None

    def to_be_visible(self, timeout=None):
        self._check(self._loc.is_visible, "to_be_visible()", timeout)

    def to_be_hidden(self, timeout=None):
        self._check(self._loc.is_hidden, "to_be_hidden()", timeout)

    def to_have_value(self, value, timeout=None):
        self._check(lambda: self._loc.input_value(0) == value,
                    f"to_have_value({value!r})", timeout)

    def to_contain_text(self, text, timeout=None):
        self._check(lambda: text.upper() in self._loc.text_content(0).upper(),
                    f"to_contain_text({text!r})", timeout)

    def to_have_count(self, n, timeout=None):
        self._check(lambda: self._loc.count() == n, f"to_have_count({n})", timeout)


class _PageAssertions:
    def __init__(self, page, negate=False):
        self._page, self._neg = page, negate

    @property
    def not_(self):
        return _PageAssertions(self._page, not self._neg)

    def to_contain_text(self, text, timeout=None):
        _LocatorAssertions(self._page.get_by_text(text), self._neg) \
            .to_be_visible(timeout)


def expect(target):
    if isinstance(target, Locator):
        return _LocatorAssertions(target)
    if isinstance(target, Terminal):
        return _PageAssertions(target)
    raise TypeError("expect() takes a Locator or Terminal")


# ---------------------------------------------------------------------------
# Example
# ---------------------------------------------------------------------------
if __name__ == "__main__":
    DLL = r"C:\Program Files (x86)\IBM\Personal Communications\pcshll32.dll"

    with Terminal(DLL, session="A") as page:
        page.set_default_timeout(20_000)

        page.get_by_label("USERID").fill("MYUSER")
        page.get_by_label("PASSWORD").fill("MYPASS")
        page.keyboard.press("Enter")

        expect(page.get_by_text("MAIN MENU")).to_be_visible()
        expect(page.get_by_text("INVALID")).not_.to_be_visible(timeout=0)

        page.get_by_label("OPTION").fill("2")
        page.keyboard.press("Enter")
        page.get_by_text("PROCESSING").wait_for(state="hidden", timeout=120_000)

        print(page.get_by_text("BALANCE").value_after(12))
        page.screenshot("last_screen.txt")
        page.keyboard.press("F3")
