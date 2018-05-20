package capture;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Screenshot {
	
			public static void main(String[] args) throws IOException  
		{
			RemoteWebDriver driver;
			System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
			
			driver= new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
			
			
			File src=driver.getScreenshotAs(OutputType.FILE);
			File desc=new File("./snaps/img1.jpg");
			FileUtils.copyFile(src, desc);
			driver.quit();
	
			
			
			
			/*File src=driver.getScreenshotAs(OutputType.FILE);
			File desc=new File("./snaps/img1.jpg");
			FileUtils.copyFile(src, desc);*/
					
			
		}
	
}

