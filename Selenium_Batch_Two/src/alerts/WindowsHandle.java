package alerts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsHandle {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		// Create a new instance of the Chrome driver
		        System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		        driver = new ChromeDriver();
		        driver.manage().deleteAllCookies();
		        driver.manage().window().maximize();
		        
         // Put an Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
		       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
       // Launch the URL
		          driver.get("file:///C:/Users/HA331754/Desktop/window.html");
		 
     // Store and Print the name of the First window on the console
		          String handle= driver.getWindowHandle();
		          System.out.println(driver.getTitle());
		     	  //System.out.println("handle name is "+handle);
		 		  
   // Click on the Button "New Message Window"
 		 		 driver.findElement(By.xpath("//button[@onclick='newBrwWin()']")).click();
 		 		 
 		 		 
   // Store and Print the name of all the windows open               
		 
		        Set handles = driver.getWindowHandles();
		         // System.out.println("new window "+handles);
		 
 // Pass a window handle to the other window
		          for (String handle1 : driver.getWindowHandles()) {
		        	  System.out.println(driver.getTitle());
		        //System.out.println("new window message "+handle1);
		 
		        driver.switchTo().window(handle1);
		        driver.manage().window().maximize();
		        Thread.sleep(5000);
		        }
		 
		        // Closing Pop Up window
		          driver.close();
		 
		        // Close Original window
		         driver.quit();

	}

}
