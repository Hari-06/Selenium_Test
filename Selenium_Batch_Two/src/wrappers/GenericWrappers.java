package wrappers;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



public class GenericWrappers implements Wrappers{
	RemoteWebDriver driver;
	int i = 1;
	public String Screenshot;

	public void invokeApp(String browser, String url) {
		try {
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./lib/drivers/chromedriver.exe");
				driver = new ChromeDriver();	
			} else if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", "./lib/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", "./lib/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();


		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		finally{
			takeSnap();
		}
	}

	public void implicitwait(int sec){
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		System.out.println("wait is set for " +sec+ "seconds");
	}
	public void enterById(String idValue, String data) {
		try {
			driver.findElementById(idValue).clear();
			driver.findElementById(idValue).sendKeys(data);
			System.out.println("Element"+idValue+"found");
		}
		catch (NoSuchElementException e) {
			System.err.println("No element such as"+idValue+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+idValue);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		finally{
			//takeSnap();
		}
	}
	public void enterByName(String nameValue, String data) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByName(nameValue).clear();
			driver.findElementByName(nameValue).sendKeys(data);
			System.out.println("Element"+nameValue+"found");
		} 
		catch (NoSuchElementException e) {
			System.err.println("No element such as"+nameValue+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+nameValue);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		finally{
			//takeSnap();
		}

	}

	public void enterByXpath(String xpathValue, String data) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(xpathValue).clear();
			driver.findElementByXPath(xpathValue).sendKeys(data);
			System.out.println("Element"+xpathValue+"found");
		} 
		catch (NoSuchElementException e) {
			System.err.println("No element such as"+xpathValue+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+xpathValue);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		finally{
			//takeSnap();
		}
	}

	public boolean verifyTitle(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	public void verifytitle(String title) {
		try {
			String mytitle="View Lead | opentaps CRM";
			title=driver.getTitle();
			if(title.equalsIgnoreCase(mytitle)){
				System.out.println("title matches");
			}
			else
			{
				System.out.println("title not matches");
			}
		} 
		catch (NoSuchElementException e) {
			System.err.println("No element such as"+title+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+title);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void verifyTextById(String id, String text) {
		// TODO Auto-generated method stub
		try {
			String txtofid = driver.findElementById(id).getText();
			if(txtofid.equals(text)){
				System.out.println(text +"String matches");
			}
			else
			{
				System.out.println("string not matches");
			}
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+id+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+id);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}

	}

	public void verifyTextByName(String name, String text) {
		// TODO Auto-generated method stub
		String name1 = driver.findElementById(name).getText();
		try {
			if(name1.equals(text)){
				System.out.println(text +"String matches");
			}
			else
			{
				System.out.println("string not matches");
			}
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+name+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+name);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}

	}
	public void verifyTextByXpath(String xpath, String text) {
		// TODO Auto-generated method stub
		try {
			String txtofid = driver.findElementByXPath(xpath).getText();
			if(txtofid.equals(text)){
				System.out.println("String matches");
			}
			else
			{
				System.out.println("string not matches");
			}
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+xpath+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+xpath);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void verifyTextContainsByXpath(String xpath, String text) {
		// TODO Auto-generated method stub

	}

	public void clickById(String id) {
		// TODO Auto-generated method stub
		try {
			driver.findElementById(id).click();
			System.out.println("Element"+id+"is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+id+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+id);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}

	}

	public void clickByClassName(String classVal) {
		try {
			driver.findElementByClassName(classVal).click();
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+classVal+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+classVal);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void clickByName(String name) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByName(name).click();
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+name+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+name);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}
	
	///////////////////////////////////////////////////////
	
	public WebElement FindByName(String name) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByName(name);
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+name+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+name);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
		return null;
	}
	
	
	//////////////////////////////////////////////

	public void clickByLink(String name) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByLinkText(name).click();	
			takeSnap();
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+name+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+name);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void clickByLinkNoSnap(String name) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByLinkText(name).click();
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+name+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+name);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void clickByXpath(String xpathVal) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(xpathVal).click();
			takeSnap();
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+xpathVal+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+xpathVal);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void clickByXpathNoSnap(String xpathVal) {
		// TODO Auto-generated method stub
		try {
			driver.findElementByXPath(xpathVal).click();
			System.out.println("Element"+ xpathVal+"is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+xpathVal+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+xpathVal);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public String getTextById(String idVal) {
		// TODO Auto-generated method stub
		try {
			String txtofid = driver.findElementById(idVal).getText();
			return txtofid;
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+idVal+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+idVal);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public String getTextByXpath(String xpathVal) {
		// TODO Auto-generated method stub
		try {
			String txtofid = driver.findElementByXPath(xpathVal).getText();
			return txtofid;
		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+xpathVal+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+xpathVal);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void selectVisibileTextById(String id, String value) {
		// TODO Auto-generated method stub
		try {
			WebElement dropdown= driver.findElementById(id);
			dropdown.sendKeys(value);

		} catch (NoSuchElementException e) {
			System.err.println("No element such as"+id+"found");
			throw new RuntimeException();
		} 
		catch (ElementNotInteractableException e) {
			System.err.println("Not able to interact with"+id);
			throw new RuntimeException();
		}
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Unknown Issue");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void selectIndexById(String id, int value) {
		// TODO Auto-generated method stub

	}

	public void switchToParentWindow() {
		// TODO Auto-generated method stub
		try {
			Set <String> allwin = driver.getWindowHandles();
			for(String eachwindow:allwin){
				driver.switchTo().window(eachwindow);
				break;
			}
			//		String winhand2 = driver.getWindowHandle();
			//		driver.close();
			//		driver.switchTo().window(

			String url=driver.getCurrentUrl();
			System.out.println("Current window URL is " + url);
			String title=driver.getTitle();
			System.out.println("Current window title is " + title);
		} 
		catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Window not available");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void switchToLastWindow() {
		// TODO Auto-generated method stub
		try {
			Set <String> allwin = driver.getWindowHandles();
			for(String eachwindow:allwin){
				driver.switchTo().window(eachwindow);
			}
			String url=driver.getCurrentUrl();
			System.out.println("Current window URL is " + url);
			String title=driver.getTitle();
			System.out.println("Current window title is " + title);
		} catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Window not available");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}

	}
	public void switchTospecificwindow() {
		// TODO Auto-generated method stub
		try {
			int i=1;
			Set <String> allwin = driver.getWindowHandles();
			for(String eachwindow:allwin){
				driver.switchTo().window(eachwindow);
				i=i+1;
				if(i==2){
					break;
				}
			}
			String url=driver.getCurrentUrl();
			System.out.println("Current window URL is " + url);
			String title=driver.getTitle();
			System.out.println("Current window title is " + title);
		} catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			System.err.println("Window not available");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}


	}


	public void acceptAlert() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			System.err.println("Not able to handle the alert");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void dismissAlert() {
		// TODO Auto-generated method stub
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			System.err.println("Not able to handle the alert");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public String getAlertText() {
		// TODO Auto-generated method stub
		try {
			String txt=driver.switchTo().alert().getText();
			return txt;
		} catch (Exception e) {
			System.err.println("Not able to get the text");
			e.printStackTrace();
			throw new RuntimeException();	

		}
		finally{
			//takeSnap();
		}
	}

	public void takeSnap() {
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./snaps/snap"+i+".jpg");
		Screenshot = desc.toString();
		try {
			FileUtils.copyFile(src, desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;

	}

	public void closeBrowser() {
		try {
			driver.close();
		} catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}

	}

	public void geturl() {
		try {
			String url=driver.getCurrentUrl();
			System.out.println("Current window URL is " + url);
		} catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("URL not available");
		}
	}

	public void gettitle() {

		try {
			String title=driver.getTitle();
			System.out.println("Current window title is " + title);
		} catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Title not available");
		}

	}


	public void closeAllBrowsers() {
		// TODO Auto-generated method stub
		try {
			driver.quit();
		}  catch (WebDriverException e) {
			System.err.println("Browser not reachable");
			throw new RuntimeException();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("browser not available");
		}

	}
	
	public void switchintoframebyid(int id){
		driver.switchTo().frame(id);
	}
	
	public void switchintoframebyindex(int index){
		driver.switchTo().frame(index);
	}
	
	public void switchintoframebywebelement(int we){
		driver.switchTo().frame(we);
	}

	public void verifyTextByname(String name, String text) {
		// TODO Auto-generated method stub

	}

	public void verifyTextlinktext(String linktext, String text) {
		// TODO Auto-generated method stub

	}

}
