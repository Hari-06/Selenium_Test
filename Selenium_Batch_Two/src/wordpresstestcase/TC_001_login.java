package wordpresstestcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import wordpresspages.Loginwordpress;
import wrappers.wordpressWrappers;

public class TC_001_login extends wordpressWrappers{
	@BeforeClass
	public void testDetails(){
	
		testCaseName = "TC_001_Login";
		testDescription = "Login to wordpress";
		category = "Smoke";
		authors = "Hari";
		browserName = "chrome";
		dataSheetName = "Login1";
		
	}
	
	@Test(dataProvider="fetchData")
	public void login(String uname,String pwd){
		new Loginwordpress(driver,test)
		.enterusername(uname)
		.clickcontinue()
		.enterpassword(pwd)
		.clickLoginbutton();
		
	}

}
