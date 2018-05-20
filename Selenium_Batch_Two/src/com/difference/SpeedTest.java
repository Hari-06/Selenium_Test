package com.difference;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpeedTest {

	public static void main(String[] args) {
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://wordpress.com/log-in");
		// Using Xpath 
		long XPathStartTime = System.currentTimeMillis();
		driver.findElement(By.xpath("//input[@id='usernameOrEmail']")).sendKeys("Selenium.batchone@gmail.com");              //Id Locator
		driver.findElement(By.xpath("//button[@class='button form-button is-primary']")).click();         //Xpath with class
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("selenium2017");                                //Name Locator
		driver.findElement(By.xpath("//button[@class='button form-button is-primary']")).click();       //Xpath with class
		String wc = driver.findElement(By.xpath("//span[text()='My Site']")).getText();                //Xpath text
		System.out.println("HomePage Title is: "+wc);
		driver.findElement(By.xpath("//img[@alt='Me']")).click();                                 // css selector -> attribute
		String username =driver.findElement(By.xpath("//h2[text()='seleniumbatchone']")).getText();     //css selector -> class
		System.out.println("Username is: "+username);
		driver.findElement(By.xpath("//button[text()='Sign Out']")).click();
		long XPathEndTime   = System.currentTimeMillis();
		long totalTime = XPathEndTime - XPathStartTime;
		System.out.println("XPath elapsed time = " +totalTime+" Miliiseconds");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Sign in page
		driver.findElement(By.xpath("//a[text()='Sign In']")).click();
		//Using Css Selector
		long CssStartTime = System.currentTimeMillis();
		driver.findElement(By.cssSelector("input#usernameOrEmail")).sendKeys("Selenium.batchone@gmail.com");
		driver.findElement(By.cssSelector("button[type='Submit']")).click();
		driver.findElement(By.cssSelector("input#password")).sendKeys("selenium2017");
		driver.findElement(By.cssSelector("button[type='Submit']")).click();
		String Title = driver.findElement(By.cssSelector("span.masterbar__item-content")).getText();   
		System.out.println("HomePage Title is: "+Title);
		driver.findElement(By.cssSelector("img[alt='Me']")).click();
		String user =driver.findElement(By.cssSelector("h2.profile-gravatar__user-display-name")).getText();
		System.out.println("Username is: "+user);
		driver.findElement(By.cssSelector("button[title='Sign out of WordPress.com']")).click();
		long CssEndTime = System.currentTimeMillis();
		long totTime = CssEndTime - CssStartTime;
		System.out.println("Css elapsed time = " +totTime+" Miliiseconds");
		driver.quit();

	}

}
