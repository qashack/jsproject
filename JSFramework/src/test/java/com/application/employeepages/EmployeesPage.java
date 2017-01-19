package com.application.employeepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.libraries.ExtentTestManager;
import com.application.libraries.GenericUtils;
import com.relevantcodes.extentreports.LogStatus;

public class EmployeesPage extends EmployeesGenericPage{
	
	WebDriver driver;
	
	public EmployeesPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Elements
	@FindBy(id="c")
	private WebElement companyNameDropdown;
	
	@FindBy(id="u")
	private WebElement usernameTextbox;
	
	@FindBy(id="pwd")
	private WebElement passwordTextbox;
	
	@FindBy(id="fn")
	private WebElement firstNameTexbox;
	
	@FindBy(id="ln")
	private WebElement lastNameTexbox;
	
	@FindBy(id="dob")
	private WebElement dobField;
	
	public void selectCompany(String companyName){
		GenericUtils.selectbyVisibletext(companyNameDropdown, companyName);		
	}
	
	public void typeUserName(String uname){
		usernameTextbox.sendKeys(uname);
	}
	
	public void typePassword(String pwd){
		passwordTextbox.sendKeys(pwd);
	}
	
	public void typeFirstName(String firstName){
		firstNameTexbox.sendKeys(firstName);
	}
	
	public void typeLastName(String lastName){
		lastNameTexbox.sendKeys(lastName);
	}
	
	public void typeDOB(String dob){
		dobField.clear();
		dobField.sendKeys(dob);
	}
	
	public void addNewEmployee(String companyName, String uname, String pwd, String firstName, String lastName, String dob){
		clickAddButton();
		selectCompany(companyName);
		typeUserName(uname);
		typePassword(pwd);
		typeFirstName(firstName);
		typeLastName(lastName);
		typeDOB(dob);
		clickSaveButton();		
	}
	
	public void verifyEmployeeCreated(String employeeName, String expectedSuccessMessage){
		verifyRecordCreateMessage(expectedSuccessMessage);
		clickEmployeesIcon();
		verifyRecordCreated(employeeName);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Employee "+employeeName+" created successfully");
	}
	
	public void deleteEmployee(String empName){
		rightClickOnRecord(empName);
		clickDeleteRecord();
		clickConfirmRecordDeletion();		
	}
	public void verifyEmployeeDeleted(String employeeName, String expectedSuccessMessage){
		verifyRecordDeleteMessage(expectedSuccessMessage);
		navigateToEmployees();
		verifyRecordDeleted(employeeName);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Employee "+employeeName+" deleted successfully");
	}
	
}
