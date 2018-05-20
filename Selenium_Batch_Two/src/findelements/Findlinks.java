package findelements;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Findlinks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;

		// Create a new instance of the Chrome driver
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://newtours.demoaut.com/");
		
		List<WebElement> objlinks=driver.findElements(By.tagName("a"));
		for(WebElement eachlink:objlinks) {
			String linktxt=eachlink.getText();
			System.out.println(linktxt);
		}
		
	}

}
