package alerts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import accessspecifier.Student;

public class Frames {

	public static void main(String[] args) throws InterruptedException {
		RemoteWebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("file:///C:/Users/HA331754/Desktop/frame_new.html");
		
		driver.switchTo().frame("IF1");
		String title = driver.findElementByXPath("//h1[text()=' Introduction to Selenium ']").getText();
		System.out.println(title);
	
	    WebElement frameElement = driver.findElementByXPath("//iframe[@title='frame3']");
		driver.switchTo().frame(frameElement);
		driver.findElementByXPath("//button[text()='Simple Alert']").click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();;
		
		
	driver.quit();

	}

}
