"""
End-to-end test: Playwright drives the web app, terminal_playwright drives
the mainframe emulator. Same style of API on both sides.

Install:  pip install pytest pytest-playwright && playwright install chromium
Run:      pytest test_end_to_end.py --headed

Put hllapi.py and terminal_playwright.py in the same folder.
Use 32-bit Python if your emulator DLL is 32-bit.
"""

import os

import pytest
from playwright.sync_api import Page, expect as web_expect

from terminal_playwright import Terminal, expect as mf_expect

EMULATOR_DLL = r"C:\Program Files (x86)\IBM\Personal Communications\pcshll32.dll"
SESSION = "A"
WEB_URL = "https://your-web-app.example.com"

MF_USER = os.environ.get("MF_USER", "MYUSER")
MF_PASS = os.environ.get("MF_PASS", "")


# ---- fixtures -------------------------------------------------------------
# `page` (browser) comes from pytest-playwright automatically.

@pytest.fixture(scope="session")
def mainframe():
    """One emulator connection for the whole test run, logged in once."""
    with Terminal(EMULATOR_DLL, session=SESSION) as term:
        term.set_default_timeout(30_000)
        term.get_by_label("USERID").fill(MF_USER)
        term.get_by_label("PASSWORD").fill(MF_PASS)
        term.keyboard.press("Enter")
        mf_expect(term.get_by_text("MAIN MENU")).to_be_visible()
        yield term
        term.keyboard.press("F3")  # log off / back out


# ---- test -----------------------------------------------------------------
def test_order_created_on_web_reaches_mainframe(page: Page, mainframe: Terminal):
    # 1. Web app: create an order
    page.goto(WEB_URL)
    page.get_by_label("Customer ID").fill("100245")
    page.get_by_label("Quantity").fill("3")
    page.get_by_role("button", name="Submit order").click()
    web_expect(page.get_by_text("Order created")).to_be_visible()
    order_no = page.get_by_test_id("order-number").inner_text().strip()

    # 2. Mainframe: look the order up
    mainframe.get_by_label("OPTION").fill("2")          # e.g. Order inquiry
    mainframe.keyboard.press("Enter")
    mainframe.get_by_label("ORDER NO").fill(order_no)
    mainframe.keyboard.press("Enter")

    # 3. Verify the data matches
    mf_expect(mainframe.get_by_text("ORDER FOUND")).to_be_visible()
    mf_expect(mainframe.get_by_text("NOT FOUND")).not_.to_be_visible(timeout=0)
    assert mainframe.get_by_text("CUSTOMER:").value_after(6) == "100245"
    assert mainframe.get_by_text("QTY:").value_after(3).lstrip("0") == "3"

    mainframe.keyboard.press("F3")                       # back to main menu


# Tip: on failure, the mainframe expect() error already includes the screen
# text. Save it as evidence with mainframe.screenshot("mf_screen.txt").
