package findelements;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
	       System.setProperty("webdriver.chrome.driver",".\\lib\\drivers\\chromedriver.exe");
	        // Launch browser
	driver = new ChromeDriver();

	// Maximize window
	driver.manage().window().maximize();

	// Navigate to the URL
	driver.get("file:///C:/Users/HA331754/Desktop/Table.html#");

	// Sleep for 5 seconds
	Thread.sleep(5000);

	List<WebElement> rows = driver.findElements(By.tagName("tr") );

	// print the total number of elements
	System.out.println("Total selected rows are " + (rows.size()-1));

	
	
	List<WebElement> tags =driver.findElements(By.tagName("td"));
	for(WebElement eachlink:tags) {
		String data =eachlink.getText();
		System.out.print(data+" ");
		
	}
	System.out.println();
	/*// Now using Iterator we will iterate all elements
	Iterator<WebElement> iter = rows.iterator();

	// this will check whether list has some element or not
	while (iter.hasNext()) {

	// Iterate one by one
	WebElement item = iter.next();

	// get the text
	String label = item.getText();

	// print the text
	System.out.println(label);

	}*/




	}

}
