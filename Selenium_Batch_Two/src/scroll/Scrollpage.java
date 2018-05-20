package scroll;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Scrollpage {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;

		// Create a new instance of the Chrome driver
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("http://jqueryui.com");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("scroll(0,600)");
	}
}


