package aui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class clickAndHold1 {

	public static void main(String[] args) {
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
