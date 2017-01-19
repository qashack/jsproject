package com.application.libraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericUtils.
 *
 * @author TYSS-NEW
 */
public class GenericUtils {
	
	/** The driver. */
	public WebDriver driver;
	
	/*The below methods are related to Select drop down.*/
	/**
	 * Select option by visible text. 
	 * Works only on Select HTML tag elements
	 * 
	 *@param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param text - The visible text of the option
	 */
	public static void selectbyVisibletext(WebElement element, String text)
	{		
		Select sct = new Select(element);
		sct.selectByVisibleText(text);	
	}
	
	/**
	 * Select option by value.
	 * Works only on Select HTML tag elements
	 * 
	 *@param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param value - value of the value attribute
	 */
	public static void selectbyValue(WebElement element, String value)
	{		
		Select sct = new Select(element);
		sct.selectByValue(value);
	}
	
	/**
	 * Select option by index.
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param index - zero based index, first option index is zero
	 */
	public static void selectbyIndex(WebElement element, int index)
	{
		Select sct = new Select(element);
		sct.selectByIndex(index);	
	}
	
	/**
	 * DeSelect option by index(zero based index, first option index is zero). Works only on multi-select drop down.
	 * Works only on Select HTML tag elements
	 * 
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param index - zero based index, first option index is zero
	 */
	public static void deSelectbyIndex(WebElement element, int index)
	{
		Select sct = new Select(element);
		sct.deselectByIndex(index);	
	}
	
	/**
	 * DeSelect option by value(value of the value attribute). Works only on multi-select drop down.
	 * Works only on Select HTML tag elements
	 * 
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param value - value of the value attribute
	 */
	public static void deSelectbyValue(WebElement element, String value)
	{
		Select sct = new Select(element);
		sct.deselectByValue(value);
	}
	
	/**
	 * DeSelect option by visible text. Works only on multi-select drop down. 
	 * Works only on Select HTML tag elements
	 * 
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @param text - The visible text of the option
	 */
	public static void deSelectbyVisibleText(WebElement element, String text)
	{
		Select sct = new Select(element);
		sct.deselectByVisibleText(text);	
	}
	
	/**
	 * DeSelect all options. Works only on multi-select drop down.
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 */
	public static void deSelectAll(WebElement element)
	{
		Select sct = new Select(element);
		sct.deselectAll();	
	}
	
	/**
	 * Checks if drop down list is multi-select.\
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @return true, if is multiple
	 */
	public static boolean isMultiple(WebElement element)
	{
		Select sct = new Select(element);
		return sct.isMultiple();	
	}
	
	/**
	 * Returns a collection of all the options in the drop down as a List&lt;WebElement&gt;.
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @return the options as a List&lt;WebElement&gt;
	 */
	public static List<WebElement> getOptions(WebElement element)
	{
		Select sct = new Select(element);
		return sct.getOptions();	
	}

	/**
	 * Returns a collection of all the selected options from multi-select drop down as a List&lt;WebElement&gt;.
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @return the selected options as a List&lt;WebElement&gt;
	 */
	public static List<WebElement> getSelectedOptions(WebElement element)
	{
		Select sct = new Select(element);
		return sct.getAllSelectedOptions();	
	}
	
	/**
	 * Gets the first selected option. This can be used to get the selected option from a single select drop down
	 * Works only on Select HTML tag elements
	 *
	 * @param element - The drop down element identified using @FindBy / driver.findElement()
	 * @return the first selected option as a WebElement
	 */
	public static WebElement getFirstSelectedOption(WebElement element)
	{
		Select sct = new Select(element);
		return sct.getFirstSelectedOption();	
	}
	/**
	  * Switch to window by using the title of the window/browser.
	  * 
	  * @param title - The browser title
	  * @param driver - WebDriver
	*/
	public void switchToWindow(String title, WebDriver driver){
		boolean flag=false;
		Set<String> wndHandles = driver.getWindowHandles();
		Iterator<String> it = wndHandles.iterator();
		while(it.hasNext()){
			driver.switchTo().window(it.next());
			if(driver.getTitle().equals(title)){
				System.out.println("Switched to window with the title "+title);
				flag=true;
				break;
			}
		}
		if(!flag){
			System.out.println("No window matching the title "+title);
		}
	 }
	 
	 /**
	  * Switch to window by checking for the presence of a particular element on that window.
	  *
	  * @param locator - The element locator (id,name,linkText,className,xpath,cssSelector,tagName,partialLinkText)
	  * @param locatorVal - The locator value
	  * @param driver - WebDriver
	 */
	 public void switchToWindow(String locator, String locatorVal, WebDriver driver){
		 boolean flag=false;
		 Set<String> wndHandles = driver.getWindowHandles();
		 Iterator<String> it = wndHandles.iterator();
		 List<WebElement> elements = null;
		 if(locator.equals("id")){
			 elements=driver.findElements(By.id(locatorVal));
		 }
		 else if(locator.equals("name")){
			 elements=driver.findElements(By.name(locatorVal));
		 }
		 else if(locator.equals("linkText")){
			 elements=driver.findElements(By.linkText(locatorVal));
		 }
		 else if(locator.equals("className")){
			 elements=driver.findElements(By.className(locatorVal));
		 }
		 else if(locator.equals("xpath")){
			 elements=driver.findElements(By.xpath(locatorVal));
		 }
		 else if(locator.equals("cssSelector")){
			 elements=driver.findElements(By.cssSelector(locatorVal));
		 }
		 else if(locator.equals("tagName")){
			 elements=driver.findElements(By.tagName(locatorVal));
		 }
		 else if(locator.equals("partialLinkText")){
			 elements=driver.findElements(By.partialLinkText(locatorVal));
		 }
		 else{
			 System.out.println("Wrong locator type");
		 }
		 while(it.hasNext()){
			 driver.switchTo().window(it.next());
			 if(elements.size()>0){
				 System.out.println("Switched to window with the element "+locatorVal);
				 flag=true;
				 break;
			 }   
		 }
		 if(!flag){
			 System.out.println("No window matching the title "+locatorVal);
		 }
	 }
	
	 	/**
		 * Accept alert (Clicks OK button on a JavaScript Alert/Confirmation popup).
		 *
		 * @param driver - Driver object
		 */
		public static void acceptAlert(WebDriver driver) 
		{
			try {
				driver.switchTo().alert().accept();
			} catch (Exception e) {
				System.out.println("No Alert / Confirmation popup visible");
			}			
		}
		
		/**
		 * Dismiss alert(Clicks Cancel button on a JavaScript Confirmation popup).
		 *
		 * @param driver - Driver object
		 */
		public static void dismissAlert(WebDriver driver) 
		{
			try {
				driver.switchTo().alert().dismiss();
			} catch (Exception e) {
				System.out.println("No Alert / Confirmation popup visible");
			}	
		}	
		
		/**
		 * Returns the text of JavaScript Alert/Confirmation popup.
		 *
		 * @param driver - Driver object
		 * @return the text of alert
		 */
		public static String getTextofAlert(WebDriver driver) 
		{
			String retVal=null;
			try {
				retVal = driver.switchTo().alert().getText();
			} catch (Exception e) {
				System.out.println("No Alert / Confirmation popup visible");
			}	
			return retVal;
		}
		
	/**
	 * Mouse over on any WebElement.
	 *
	 * @param driver - Driver object
	 * @param element - WebElement object
	 */
	public static void mouseOver(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
		try {
			Thread.sleep(Long.parseLong(GenericUtils.getConfigProperties("config/config.properties", "CUSTOM_STEP_DELAY")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public static void dragAndDrop(WebDriver driver, WebElement source, WebElement destination)
	{
		Actions act = new Actions(driver);
		act.clickAndHold(source).moveToElement(destination).release().perform();
	}
	public static void doubleClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}	
	public static void doubleClickOnElement(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}	
	public static void rightClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	public static void rightClickOnElement(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	public static void keyDown(WebDriver driver, Keys thekey)
	{
		Actions act = new Actions(driver);
		act.keyDown(thekey).perform();
	}
	
	public static void keyDownOnElement(WebDriver driver, WebElement element ,Keys thekey)
	{
		Actions act = new Actions(driver);
		act.keyDown(element, thekey).perform();
	}
	public static void keyUp(WebDriver driver,Keys thekey)
	{
		Actions act = new Actions(driver);
		act.keyUp(thekey).perform();
	}
	public static void keyUpOnElement(WebDriver driver, WebElement element ,Keys thekey)
	{
		Actions act = new Actions(driver);
		act.keyUp(element, thekey).perform();
	}
	public static void clickDropDownSubMenuLevel1(WebDriver driver, WebElement parentMenu, WebElement subMenu)
	{
		mouseOver(driver,parentMenu);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(subMenu));
		subMenu.click();
	}
	public static void clickDropDownSubMenuLevel2(WebDriver driver, WebElement parentMenu, WebElement subMenuLevel1, WebElement subMenuLevel2)
	{
		mouseOver(driver,parentMenu);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(subMenuLevel1));
		mouseOver(driver, subMenuLevel1);
		wait.until(ExpectedConditions.visibilityOf(subMenuLevel2));
		subMenuLevel2.click();
	}
	
	public static String getConfigProperties(String filePath, String key){
		String retVal=null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(fis);
			retVal = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		return retVal;
	}
}
