package grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGridChrome {

	WebDriver driver;
	String baseURL, nodeURL;
	 
	@BeforeTest
	public void setup() throws MalformedURLException 
	{
	baseURL = "http://flipkart.com";
	nodeURL = "http://10.149.10.95:4546/wd/hub";
	DesiredCapabilities capability = DesiredCapabilities.chrome();
	capability.setBrowserName("chrome");
	capability.setPlatform(Platform.WIN10);
	driver = new RemoteWebDriver(new URL(nodeURL), capability);
	 
	}
	 
	 
	@Test
	public void simpleTest() throws InterruptedException
	    {
	driver.get(baseURL);
	Thread.sleep(3000);
	    }
	 
	@AfterTest
	public void close()
	{
	driver.quit();
	 
	}
}
