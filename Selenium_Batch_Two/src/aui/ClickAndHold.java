package aui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import extentreports.Report1;

public class ClickAndHold extends Report1 {

	@Test
	public void click_and_Hold() throws Exception
	{
		test = extent.createTest("Click and Hold","TC_01");
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		//Load URL
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		//WebElement fr=driver.findElementByClassName("demo-frame");

		Actions builder=new Actions(driver);

		WebElement fr=driver.findElementByClassName("demo-frame");
		driver.switchTo().frame(fr);
		WebElement item1=	driver.findElementByXPath("//li[text()='Item 1']");
		int item5=driver.findElementByXPath("//li[text()='Item 5']/span").getLocation().getY();
		builder.clickAndHold(item1).moveByOffset(0, item5).release().build().perform();
		Thread.sleep(3000);
		
		driver.quit();
	}

}
