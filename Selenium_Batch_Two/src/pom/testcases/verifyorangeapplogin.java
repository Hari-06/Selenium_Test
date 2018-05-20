package pom.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pom.pages.Loginpage;

public class verifyorangeapplogin {
	@Test
	public void verifylogin() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://opensource.demo.orangehrmlive.com/");
		Loginpage login=new Loginpage(driver);
		login.verifylogin("Admin","admin");
		driver.quit();
	}
}
