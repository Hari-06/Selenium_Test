package testng_demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Draganddrop {
@Test
	public void draganddrop(){
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Load URL
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		//WebElement fr=driver.findElementByClassName("demo-frame");
		
		Actions builder=new Actions(driver);
		
		WebElement fr=driver.findElementByClassName("demo-frame");
		driver.switchTo().frame(fr);
		WebElement drag=driver.findElementById("draggable");
		WebElement drop=driver.findElementById("droppable");
		builder.dragAndDrop(drag, drop).perform();
	}

}
