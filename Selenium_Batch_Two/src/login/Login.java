package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://opensource.demo.orangehrmlive.com/");
	
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@class='button']")).click();
		String wc = driver.findElement(By.xpath("//a[text()='Welcome Admin']")).getText();
		System.out.println(wc);
		driver.findElement(By.partialLinkText("Welcome")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		driver.quit();
		
		
		
		
		
		
		
		

	}

}
