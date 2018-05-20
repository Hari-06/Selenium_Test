package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome {

	public static void main(String[] args) {
		
		//Creating an object for WebDriver(Interface)
		WebDriver driver; 
		
		//Setting up the driver path
		System.setProperty("webdriver.chrome.driver", ".//lib//drivers//chromedriver.exe");
		
		//Creating an Instance for Chrome Driver
		driver = new ChromeDriver();
		
		//Maximizing the Browser
		driver.manage().window().maximize();
		
		//Launch a URL
		driver.get("https://mywipro.wipro.com");
		

	}

}
