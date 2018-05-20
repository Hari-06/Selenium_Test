package webtable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LearnWebtable {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();		
		driver.manage().window().maximize();
		driver.get("https://erail.in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementById("txtStationFrom").clear();
		driver.findElementById("txtStationFrom").sendKeys("MS",Keys.TAB);
		driver.findElementById("txtStationTo").clear();
		driver.findElementById("txtStationTo").sendKeys("SBC",Keys.TAB);
		WebElement selectDateOnly = driver.findElementById("chkSelectDateOnly");
		// uncheck
		if(selectDateOnly.isSelected())
			selectDateOnly.click();
		WebElement table = driver.findElementByXPath("//table[@class='DataTable TrainList']");
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		System.out.println(tableRows.size());	
		WebElement firstRow = tableRows.get(0);		
		List<WebElement> columns = firstRow.findElements(By.tagName("td"));
		System.out.println(columns.size());
		// print all trains departure time
		for (int i = 0; i < tableRows.size(); i++) {
			System.out.println(tableRows.get(i).findElements(By.tagName("td")).get(3).getText());
		}

	}

}













