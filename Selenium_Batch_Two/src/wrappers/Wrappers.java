package wrappers;

import org.openqa.selenium.WebElement;

public interface Wrappers {
	/**
	 * This method will launch the given browser
	 * and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @author Hari S/Hari M - Wipro
	 * @param browser - The browser of the application to be launched (chrome, firefox)
	 * @param url - The url with http or https or file
	 * @ 
	 * 
	 */
	public void invokeApp(String browser, String url) ;

	/**
	 * This method will enter the value to the text field
	 *  using id attribute to locate
	 * 
	 * @param idValue - id of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Hari S/Hari M - Wipro
	 * @return 
	 * @ 
	 */
	public void implicitwait(int sec);
	
	public void enterById(String idValue, String data) ;
	
	/**
	 * This method will enter the value to the text field using name attribute to locate
	 * 
	 * @param nameValue - name of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Hari S/Hari M - Wipro
	 */
	public void enterByName(String nameValue, String data)  ;	
	
	/**
	 * This method will enter the value to the text field using xpath attribute to locate
	 * 
	 * @param xpathValue - name of the webelement
	 * @param data - The data to be sent to the webelement
	 * @author Hari S/Hari M - Wipro
	 */
	public void enterByXpath(String xpathValue, String data) ;


	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 * @author Hari S/Hari M - Wipro
	 */
	public boolean verifyTitle(String title);
	/**
	 * This method will verify the given text
	 * @param id - The locator of the object in id
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	public void verifytitle(String title);
	/**
	 * This method will verify the given text
	 * @param id - The locator of the object in id
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	
	
	
	public void verifyTextById(String id, String text);
	
	/**
	 * This method will verify the given text with exact match
	 * @param xpath - The locator of the object in id
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	public void verifyTextByname(String name, String text);
	/**
	 * This method will verify the given text with exact match
	 * @param xpath - The locator of the object in name
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	
	public void verifyTextlinktext(String linktext, String text);
	/**
	 * This method will verify the given text with exact match
	 * @param xpath - The locator of the object in linktext
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	
	public void verifyTextByXpath(String xpath, String text);
	
	/**
	 * This method will verify the given text with partial match
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Hari S/Hari M - Wipro
	 */
	public void verifyTextContainsByXpath(String xpath, String text);


	/**
	 * This method will click the element using id as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickById(String id)  ;

	/**
	 * This method will click the element using classname as locator
	 * @param id  The id (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 * @ 
	 */
	public void clickByClassName(String classVal) ;
	
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickByName(String name)  ;
	

	/**
	 * This method will click the element using link name as locator and do take snap
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickByLink(String name) ;
	
	/**
	 * This method will click the element using link name as locator and do not take snap
	 * @param name  The link name (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickByLinkNoSnap(String name) ;

	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickByXpath(String xpathVal) ;
	
	/**
	 * This method will click the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be clicked
	 * @author Hari S/Hari M - Wipro
	 */
	public void clickByXpathNoSnap(String xpathVal) ;
	
	/**
	 * This method will get the text of the element using id as locator
	 * @param xpathVal  The id (locator) of the element 
	 * @author Hari S/Hari M - Wipro
	 */
	public String getTextById(String idVal);

	/**
	 * This method will get the text of the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element 
	 * @author Hari S/Hari M - Wipro
	 */
	public String getTextByXpath(String xpathVal);

	/**
	 * This method will select the drop down visible text using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Hari S/Hari M - Wipro
	 */
	public void selectVisibileTextById(String id, String value) ;
	
	/**
	 * This method will select the drop down using index as id locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown 
	 * @author Hari S/Hari M - Wipro
	 */
	public void selectIndexById(String id, int value) ;
	
	/**
	 * This method will switch to the parent Window
	 * @author Hari S/Hari M - Wipro
	 */
	public void switchToParentWindow();
	
	/**
	 * This method will move the control to the last window
	 * @author Hari S/Hari M - Wipro
	 */
	
	public void switchTospecificwindow();
	/**
	 * This method will move the control to the specified window
	 * @author Hariharan - Wipro
	 */
	
	public void switchToLastWindow();
	
	/**
	 * This method will accept the alert opened
	 * @author Hari S/Hari M - Wipro
	 */
	public void acceptAlert();
	
	/**
	 * This method will dismiss the alert opened
	 * @author Hari S/Hari M - Wipro
	 */
	public void dismissAlert();
	
	/**
	 * This method will return the text of the alert
	 * @author Hari S/Hari M - Wipro
	 */
	public String getAlertText();
	
	/**
	 * This method will take snapshot of the browser
	 * @author Hari S/Hari M - Wipro
	 */
	public void takeSnap();
		
	/**
	 * This method will close the active browser
	 * @author Hari S/Hari M - Wipro
	 */
	public void closeBrowser();
	
	
	/**
	 * This method will close all the browsers
	 * @author Hari S/Hari M - Wipro
	 */
	public void closeAllBrowsers();
	public WebElement FindByName(String nameValue)  ;	

}
