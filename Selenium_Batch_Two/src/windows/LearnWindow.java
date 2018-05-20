package windows;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWindow {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;

		// Create a new instance of the Chrome driver
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://seleniumpractise.blogspot.in/2017/07/multiple-window-examples.html");
		String parent = driver.getWindowHandle();
		System.out.println("Parent window is "+parent);
		driver.findElement(By.xpath("//a[contains(@href,'http://www.google.com')]")).click();
		Set<String> allwin = driver.getWindowHandles();
		int count = allwin.size();
		System.out.println("Total window is "+count);
		for(String child:allwin) 		{
			if(!parent.equalsIgnoreCase(child)) 			{
				driver.switchTo().window(child);
				System.out.println("Child window Title is: "+driver.getTitle());
				driver.findElement(By.name("q")).sendKeys("Selenium webdriver");
				Thread.sleep(3000);
				driver.close();
			}		}
		Thread.sleep(3000);
		driver.switchTo().window(parent);
		System.out.println("Parent window title is: "+driver.getTitle());
		driver.close();


	}

}
