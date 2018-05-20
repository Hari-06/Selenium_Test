package extentTestcases;

import org.testng.annotations.Test;

import extentreports.Report;




public class TestCase_02 extends Report {
	@Test
	public void SignIn()
	{
		
		startTestCase("TC_02 ", "WordPress Login", "Sanity", "Bala");
		invokeApp("chrome", "https://wordpress.com/log-in");
		enterById("usernameOrEmail", "Selenium.batchone@gmail.com");
		clickByXpath("//button[@type='submit']");
		enterById("password", "selenium2017");
		clickByXpath("//button[@type='submit']");
		
	}
	
	@Test
	public void Verify_HomePage()
	{
		startTestCase("TC_02 ", "WordPress Homepage", "Sanity", "Bala");
		verifyTextByXpath("//span[text()='My Site']", "My Site");
		closeAllBrowsers();
		
	}
	
}
