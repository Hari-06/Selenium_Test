package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	WebDriver driver;

	By uname=By.cssSelector("input#txtUsername");
	By pwd=By.cssSelector("input#txtPassword");
	By loginbtn=By.xpath("//input[@class='button']");

	public Loginpage(WebDriver driver) {
		this.driver=driver;
	}
	public void verifylogin(String userid,String pass) {
		driver.findElement(uname).sendKeys(userid);
		driver.findElement(pwd).sendKeys(pass);
		driver.findElement(loginbtn).click();
	}

}
