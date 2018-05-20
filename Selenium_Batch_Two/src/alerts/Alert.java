package alerts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Alert {

	public static void main(String[] args) {
		RemoteWebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("file:///C:/Users/HA331754/Desktop/Alerts.html");
		
		driver.findElementByXPath("//button[text()='Simple Alert']").click();
		driver.switchTo().alert().accept(); //simple alert
		
		driver.findElementByXPath("//button[text()='Confirmation Alert']").click();
		driver.switchTo().alert().dismiss(); // confirmation alert
		
		driver.findElementByXPath("//button[text()='Prompt Alert']").click();
		
		String text = driver.switchTo().alert().getText();
		System.out.println(text);
		driver.switchTo().alert().sendKeys("Hari"); // prompt alert
		driver.switchTo().alert().accept();
		
		driver.quit();
		
		
		
	}

}
