package alerts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Window {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//lib//drivers//chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://phptravels.com/demo/");
		String Parentwindow=driver.getWindowHandle();
		driver.findElementByXPath("//small[text()='http://www.phptravels.net']").click();
		driver.findElementByXPath("//small[text()='http://www.phptravels.net/admin']").click();
		
		driver.switchTo().window(Parentwindow);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		Set<String> allWin  = driver.getWindowHandles();
		System.out.println(allWin.size());		
		
	
	//First Window
	/*	allWin  = driver.getWindowHandles();
		for (String eachWin : allWin) {
			driver.switchTo().window(eachWin);
			break;
		}
		//Last Window
		for (String eachWin : allWin) {
				driver.switchTo().window(eachWin);	
			}
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			*/
		
	/*	//Specific Window(only for Interview point of view)
		int i =1;
		allWin  = driver.getWindowHandles();
		for (String eachWin : allWin) {
			driver.switchTo().window(eachWin);			
			if(i==2){
				break;	
			}
			i++;
			
		}*/
		
		
		
		
		
		
		
		
		
		
		










	}

}
