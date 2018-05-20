package readexcel;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReaddatafromDP {
	WebDriver driver;
	@Test(dataProvider="orangeapp")
	public void readdatafromDP(String username,String password) throws InterruptedException {

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
		Object[][] data=new Object[3][2];
		data[0][0]="Admin";
		data[0][1]="admin";
		data[1][0]="Admin1";
		data[1][1]="admin2";
		data[2][0]="Admin";
		data[2][1]="admin";
		return data;
	}

}
