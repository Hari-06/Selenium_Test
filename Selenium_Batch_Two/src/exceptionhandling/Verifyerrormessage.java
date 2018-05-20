package exceptionhandling;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Verifyerrormessage {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		RemoteWebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("http://gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(2000);
String actual_error=driver.findElement(By.xpath("//div[text()='Enter a password']")).getText();
String expected_error="Enter a password";
	}

}
