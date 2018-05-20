package passwordencoder;

import java.util.concurrent.TimeUnit;

import org.apache.commons.net.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Seleniumdemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");

		String encodedstr="YXNod2luMjAxMDg5";
		driver.findElement(By.id("email")).sendKeys("hariharan.shankar201089@gmail.com");
		driver.findElement(By.id("pass")).sendKeys(decodepassword(encodedstr));
		driver.findElement(By.xpath("//input[@value='Log In']")).click();
		boolean fbicon=driver.findElement(By.xpath("//span[text()='Facebook']")).isDisplayed();
		System.out.println(fbicon);
	}

	public static  String decodepassword(String encoded) {
		byte[] decoded=Base64.decodeBase64(encoded);
		return new String(decoded);
	}
}
