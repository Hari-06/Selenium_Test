package readexcel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.ExcelDataConfig;

public class ReaddatafromExcelSelenium {
	WebDriver driver;
	@Test(dataProvider="orangeapp")
	public void readdata(String username,String password) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".\\lib\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://opensource.demo.orangehrmlive.com/");

		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys(username);
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys(password);
		driver.findElement(By.xpath("//input[@class='button']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"Enter valid credentials");
		System.out.println("Page title is verified successfully");
	}
	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}
	@DataProvider(name="orangeapp")
	public Object[][] passData(){
		ExcelDataConfig config=new ExcelDataConfig("D:\\Batch -2 Workspace\\Selenium_Batch_Two\\TestData\\orangeapp.xlsx");
		int rows=config.getRowCount(0);
		Object[][] data=new Object[rows][2];
		for(int i=0;i<rows;i++) {
			data[i][0]=config.getData(0, i, 0);
			data[i][1]=config.getData(0, i, 1);
		}
		return data;
	}

}
