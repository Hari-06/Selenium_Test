package winium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;



public class Calculator {

	public static void main(String[] args) throws InterruptedException, IOException {
	
		DesktopOptions option=new DesktopOptions();
		option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
		//WiniumDriver driver=new WiniumDriver(new URL("http://localhost:9999"),option);
		WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"),option);
		Thread.sleep(5000);
		driver.findElementByName("Seven").click();
		driver.findElementByName("Plus").click();
		driver.findElementByName("Eight").click();
		driver.findElementByName("Equals").click();
		Thread.sleep(5000);
		String result=driver.findElementById("CalculatorResults").getAttribute("Name");
		System.out.println("the output is"+ result);
	
		
	}

}