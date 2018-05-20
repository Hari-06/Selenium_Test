package aui;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import extentreports.Report1;

public class Colorpicker extends Report1 {
	@Test
	public  void color_picker() throws  Exception {
		test = extent.createTest("Color Picker","TC_03");
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		//Load URL
		driver.get("https://jqueryui.com/slider/#colorpicker");
		driver.manage().window().maximize();
		//WebElement fr=driver.findElementByClassName("demo-frame");
		
		Actions builder=new Actions(driver);
		
		WebElement fr=driver.findElementByClassName("demo-frame");
		driver.switchTo().frame(fr);
		
		WebElement slider = driver.findElementByXPath("//div[@id='red']/span");
		
		builder.clickAndHold(slider).moveByOffset(-80, 0).release().build().perform();
		//builder.clickAndHold(slider).moveByOffset(-20,0).release().build().perform();
		Thread.sleep(3000);
		screenshot();
		driver.quit();
	}

}
