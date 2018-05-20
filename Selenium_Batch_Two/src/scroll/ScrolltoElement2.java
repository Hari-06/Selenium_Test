package scroll;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrolltoElement2 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;

		// Create a new instance of the Chrome driver
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://jqueryui.com/");

		// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;

		//Identify the WebElement which will appear after scrolling down
		WebElement element = driver.findElement(By.xpath("//h3[text()='Utilities']"));
		
		je.executeScript("arguments[0].scrollIntoView(true);",element);

		System.out.println(element.getText());
	}
}


