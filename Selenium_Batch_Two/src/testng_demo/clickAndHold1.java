package testng_demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class clickAndHold1 {
@Test
	public void clickandhold() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//Load URL
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		
		Actions builder=new Actions(driver);
		
		WebElement fr=driver.findElementByClassName("demo-frame");
		driver.switchTo().frame(fr);
		
		WebElement drag=driver.findElementById("draggable");
		WebElement drop=driver.findElementById("droppable");

		builder.clickAndHold(drag).moveToElement(drop).release(drop).build().perform();

	}

}
