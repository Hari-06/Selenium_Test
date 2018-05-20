package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InternetExplorer {

	public static void main(String[] args) {
		        //Creating an object for WebDriver(Interface)
				WebDriver driver; 
				
						
				//Setting up the driver path
				System.setProperty("webdriver.ie.driver", ".//lib//drivers//IEDriverServer.exe");
				
				 //Initialize InternetExplorerDriver Instance.
				driver = new InternetExplorerDriver();
				
				//Maximizing the Browser
				driver.manage().window().maximize();
				
				//Launch a URL
				driver.get("https://mywipro.wipro.com");
	}

}
