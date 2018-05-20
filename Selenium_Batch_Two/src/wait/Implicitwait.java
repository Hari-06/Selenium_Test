package wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Implicitwait {

	public static void main(String[] args) {
		// Start browser
				
				System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
				
				WebDriver driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				// Start application
				driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");
		 //implicit wait:
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				// Click on timer button to start
				driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();
				
								
				String txt=driver.findElement(By.xpath("//p[text()='WebDriver']")).getText();
				System.out.println(txt);
		 
	}
	}

