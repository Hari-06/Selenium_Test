package webtable;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Calendar {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.redbus.in/");
		driver.findElement(By.xpath("//label[text()='Onward Date']")).click();
	//Thread.sleep(4000);
	//	driver.findElement(By.xpath("//label[text()='Onward Date']/following::td[26]")).click();
		driver.switchTo().alert().dismiss();
		driver.findElement(By.xpath("//label[text()='Onward Date']/following::td[26]")).click();
		driver.quit();
	}
}
