package com.application.generics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.libraries.ExtentTestManager;
import com.application.libraries.GenericUtils;
import com.application.libraries.JavascriptLibrary;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class BasePage.
 */
public class BasePage {
	
	/** The driver. */
	WebDriver driver;
	
	/** The logo. */
	@FindBy(className="WebHRLogo")
	private WebElement logo;
	
	//Admin main menu link
	/** The admin menu. */
	@FindBy(xpath="//li[@class='dropdown'][last()]")
	private WebElement adminMenu;
	
	@FindBy(xpath="//li[@class='dropdown'][last()]/ul")
	private WebElement adminMenuDropdown;
	
	//Admin menu -> sub menu links
	/** The signout link. */
	@FindBy(xpath="//a[contains(text(),'Sign Out')]")
	private WebElement signoutLink;
		
	//Organization main menu link
	/** The organization menu. */
	@FindBy(id="navOrganization")
	private WebElement organizationMenu;
	
	@FindBy(xpath="//div[@id='navOrganization']/following-sibling::ul")
	private WebElement organizationMenuDropdown;
	
	//Employees main menu link
	@FindBy(id="navEmployees")
	private WebElement employeesMenu;
	
	@FindBy(xpath="//div[@id='navEmployees']/following-sibling::ul")
	private WebElement employeesMenuDropdown;
	
	//More main menu link
	@FindBy(id="navMore")
	private WebElement moreMenuLink;
	
	@FindBy(xpath="//div[@id='navMore']/following-sibling::ul")
	private WebElement moreMenuDropdown;
	
	//Organization sub menu links
	/** The companies link. */
	@FindBy(xpath="//a[contains(text(),'Companies')]")
	private WebElement companiesLink;
	
	//Employees sub menu links
	@FindBy(xpath="//a[text()='Employees']")
	private WebElement employeesLink;
	
	//More menu sub menu links
	@FindBy(xpath="//li[@class='dropdown']//div[text()='Employees Directory']")
	private WebElement employeesDirectoryLink;
		
	@FindBy(id="WebHRGrid")
	private WebElement mainGridTable;
	
	@FindBy(css="input[type='button'][value*='Add']")
	private WebElement addButton;
	
	@FindBy(id="btnAddRecord")
	private WebElement saveButton;
	
	@FindBy(css="#jqContextMenu #delete")
	private WebElement deleteRecordContextMenu;
	
	@FindBy(css="input[value='Confirm Delete']")
	private WebElement confirmDeleteButton;
	
	@FindBy(css="#divDeleteRecord > i.silargetick")
	private List<WebElement> deleteTickmarkImage;
	
	@FindBy(id="divDeleteRecord")
	private WebElement deleteRecordMsgDiv;
	
	@FindBy(css="div.alert.alert-info")
	private WebElement recordAddedMessage;
	
	@FindBy(css="div.alert.alert-info .sisuccess")
	private List<WebElement> recordAddedTickmark;
	
	@FindBy(css="ul.dropdown-menu.pull-left")
	private WebElement menuWrapper;
	
	/**
	 * Instantiates a new base page.
	 *
	 * @param driver the driver
	 */
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
				
	/**
	 * Signout.
	 */
	public void signout(){	
		GenericUtils.mouseOver(driver, adminMenu);	
		JavascriptLibrary.unhideElement(driver, adminMenuDropdown);
		signoutLink.click();
	}
	
	/**
	 * Navigate to companies.
	 */
	public void navigateToCompanies(){		
		GenericUtils.mouseOver(driver, organizationMenu);	
		JavascriptLibrary.unhideElement(driver, organizationMenuDropdown);
		companiesLink.click();
		JavascriptLibrary.hideElement(driver, organizationMenuDropdown);
	}
	
	public void navigateToEmployees(){
		GenericUtils.mouseOver(driver, employeesMenu);	
		JavascriptLibrary.unhideElement(driver, employeesMenuDropdown);
		employeesLink.click();
		JavascriptLibrary.hideElement(driver, employeesMenuDropdown);
	}
	
	public void navigateToEmployeesDirectory(){
		GenericUtils.mouseOver(driver, moreMenuLink);	
		JavascriptLibrary.unhideElement(driver, moreMenuDropdown);
		employeesDirectoryLink.click();		
		JavascriptLibrary.hideElement(driver, moreMenuDropdown);
	}
	
	public void clickAddButton() {
		addButton.click();		
	}

	public void clickSaveButton() {
		saveButton.click();		
	}
	
	public void verifyRecordCreated(String name){
		Assert.assertEquals(driver.findElements(By.xpath("//table[@id='WebHRGrid']//td[contains(text(),'"+name+"')]")).size(), 1);
	}
	
	public void rightClickOnRecord(String recordName){
		WebElement record = driver.findElement(By.xpath("//td[contains(text(),'"+recordName+"')]"));
		GenericUtils.rightClickOnElement(driver, record);
	}
	
	public void clickDeleteRecord(){
		deleteRecordContextMenu.click();
	}
	
	public void clickConfirmRecordDeletion(){
		confirmDeleteButton.click();	
	}
	
	public void verifyRecordDeleteMessage(String expectedMessage){
		Assert.assertEquals(deleteTickmarkImage.size(), 1);
		String actualMsg = deleteRecordMsgDiv.getText();
		Assert.assertEquals(actualMsg, expectedMessage);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Expected message "+expectedMessage+" matches actual message "+actualMsg);
	}
	
	public void verifyRecordCreateMessage(String expectedMessage){
		Assert.assertEquals(recordAddedTickmark.size(), 1);
		String actualMsg = recordAddedMessage.getText();
		Assert.assertEquals(actualMsg, expectedMessage);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Expected message "+expectedMessage+" matches actual message "+actualMsg);
	}
	
	public void verifyRecordDeleted(String name){
		Assert.assertEquals(driver.findElements(By.xpath("//table[@id='WebHRGrid']//td[text()='"+name+"']")).size(), 0);
	}
}
