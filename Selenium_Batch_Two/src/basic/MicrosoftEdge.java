package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class MicrosoftEdge {

	public static void main(String[] args) {
		//Creating an object for WebDriver(Interface)
		WebDriver driver; 
		
		//Setting up the driver path
		System.setProperty("webdriver.edge.driver", ".//lib//drivers//MicrosoftWebDriver.exe");
		
		 //Initialize Microsoft Edge Driver Instance.
		driver = new EdgeDriver();
		
		//Maximizing the Browser
		driver.manage().window().maximize();
		
		//Launch a URL
		driver.get("https://mywipro.wipro.com");

	}

}
