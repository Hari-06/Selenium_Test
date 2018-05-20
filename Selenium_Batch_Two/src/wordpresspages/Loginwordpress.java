/**
 * 
 */
package wordpresspages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.GenericWrappers;
import wrappers.wordpressWrappers;

/**
 * @author Hariharan
 * 
 * This class will store all the locators and methods of word press login page
 *
 */
public class Loginwordpress extends wordpressWrappers{
	public Loginwordpress(RemoteWebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
				
		if(!verifyTitle("Log In — WordPress.com")){
			reportStep("This is not My wordpress loginpage", "FAIL");
		}		}
		
		public Loginwordpress enterusername(String data){
			enterById("usernameOrEmail", data);
			return this;
		}
		
		public Loginwordpress enterpassword(String data){
			enterById("password", data);
			return this;
		}
		
		public Loginwordpress clickcontinue(){
			clickByXpath("//button[text()='Continue']");
			return this;
		}
		
		public homepage clickLoginbutton(){
			clickByXpath("//button[text()='Log In']");
			return new homepage(driver,test);
		}
	}

	

