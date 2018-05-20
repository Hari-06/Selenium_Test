package exceldemo;

import org.testng.annotations.Test;

public class Wordpress extends wrappers.GenericWrappers {
	
	@Test(dataProvider="fetchData",dataProviderClass=exceldemo.DP.class)
	public void loginapp(String brows,String url,String uname,String pwd,String txt) throws Exception{
		invokeApp(brows,url);
		enterById("usernameOrEmail", uname);
		clickByXpath("//button[text()='Continue']");
		enterById("password", pwd);
		clickByXpath("//button[text()='Log In']");
		verifyTextByXpath("//span[text()='My Site']", txt);		
		
	}

}
