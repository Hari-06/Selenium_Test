package jenkins;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Jenkinsdemo {
	@Test
	public void jenkins() {
		System.out.println("Welcome to jenkins");
		
		System.setProperty("webdriver.chrome.driver",".\\lib\\ss\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		//Load URL
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
	}

}
