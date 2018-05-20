package aui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Rightclick {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Load URL
		driver.get("https://google.com");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		Actions builder=new Actions(driver);
		
	    WebElement RightClick =  driver.findElement(By.name("q"));
		builder.contextClick(RightClick).build().perform();
		
	//	builder.contextClick().build().perform();
	}

}
