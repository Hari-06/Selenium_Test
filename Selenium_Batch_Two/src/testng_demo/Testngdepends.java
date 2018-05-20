package testng_demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testngdepends {
WebDriver driver;
@BeforeClass
	public void startbrowser() {
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		System.out.println("==============Started browser========");
	}
@Test
	public void startapp() {
		driver.get("http://opensource.demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("orangehrmlive.com"));
		
	}
	@Test(dependsOnMethods="startapp")
	public void loginapp() {
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("hhadmin");
		driver.findElement(By.xpath("//input[@class='button']")).click();
		boolean status=driver.findElement(By.xpath("//b[text()='Admin']")).isDisplayed();
		Assert.assertTrue(status);
	}
	@Test(dependsOnMethods="loginapp")
	public void logoutapp() throws InterruptedException {
		driver.findElement(By.partialLinkText("Welcome")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.name("txtUsername")).isDisplayed());
	}
	@AfterClass
	public void closebrowser() {
		driver.quit();
	}
}
