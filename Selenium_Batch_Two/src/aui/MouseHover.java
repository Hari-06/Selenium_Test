package aui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MouseHover {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Load URL
		driver.get("https://www.flipkart.com/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _29YdH8']")));
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		WebElement app=driver.findElementByXPath("//span[text()='TVs & Appliances']");
		Actions builder=new Actions(driver);
		//Thread.sleep(5000);

	//builder.moveToElement(app).build().perform();
		builder.moveToElement(app).perform();

		Thread.sleep(5000);
		driver.findElementByXPath("//span[text()='Sony']").click();		
	}

}
