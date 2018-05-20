package aui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import extentreports.Report1;

public class Draganddrop extends Report1 {

	@Test
	public  void drag_and_Drop() throws Exception {
		test = extent.createTest("Drag and Drop","TC_02");
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
		Thread.sleep(3000);
	
		driver.quit();
		
	}

}
