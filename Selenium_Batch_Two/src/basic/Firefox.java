package basic;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox {

	public static void main(String[] args) {
		
		       //Creating an object for WebDriver(Interface)
				WebDriver driver; 
				
				//Setting up the driver path
				System.setProperty("webdriver.gecko.driver",".//lib//drivers//geckodriver.exe");
				 				
						
				//Creating an Instance for Chrome Driver
				driver = new FirefoxDriver();
				
				// By default firefox opens in a maximized mode , so need to use the below line.
				//driver.manage().window().maximize();
				
				//Launch a URL
				driver.get("https://mywipro.wipro.com");
	}

}
