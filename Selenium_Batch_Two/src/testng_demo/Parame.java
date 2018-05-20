package testng_demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import wrappers.GenericWrappers;

public class Parame extends GenericWrappers{
@Parameters({"browserName","URL","userName","password","site"})
  @Test
  public void f(String brows,String url,String uname,String pwd,String txt) throws Exception{
		invokeApp(brows,url);
		enterById("usernameOrEmail", uname);
		clickByXpath("//button[text()='Continue']");
		enterById("password", pwd);
		clickByXpath("//button[text()='Log In']");
		verifyTextByXpath("//span[text()='My Site']", txt);		
		
  }
}
