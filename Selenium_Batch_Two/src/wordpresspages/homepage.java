package wordpresspages;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import wrappers.GenericWrappers;
import wrappers.wordpressWrappers;

public class homepage extends wordpressWrappers {

	public homepage(RemoteWebDriver driver, ExtentTest test) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.test=test;
		if(!verifyTitle("Following ‹ Reader — WordPress.com")){
			reportStep("This is not wordpress homepage", "FAIL");
		}		
	}
/*public homepage verifysitename(String data){
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	verifyTextContainsByXpath("//span[text()='My Site']", data);
	return this;
}*/
}
