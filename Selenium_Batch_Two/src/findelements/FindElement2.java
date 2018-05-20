package findelements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElement2 {

	public static void main(String[] args) {
		WebDriver driver;
	       System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
	        // Launch browser
	driver = new ChromeDriver();

	// Maximize window
	driver.manage().window().maximize();
	
	driver.get("http://legacy.crystalcruises.com/Calendar.aspx");
	driver.manage().window().maximize();	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	List<WebElement> allQuote =  driver.findElements(By.linkText("Get-a-Quote"));
	int count = allQuote.size();
	System.out.println(count);
	}

}
