package com.application.organizationpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.libraries.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class CompaniesPage extends OrganizationPage {

	WebDriver driver;
	
	@FindBy(id="cn")
	private WebElement companyNameTextbox;
	
	public CompaniesPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
	public void typeCompanyName(String companyName){
		companyNameTextbox.sendKeys(companyName);
	}
	
	public void addNewCompany(String companyName){
		clickAddButton();
		typeCompanyName(companyName);
		clickSaveButton();
	}
	
	public void verifyCompanyCreated(String companyName){
		clickCompaniesIcon();
		verifyRecordCreated(companyName);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Company "+companyName+" created successfully");	
	}
	
	public void deleteCompany(String companyName, String expectedMessage){
		rightClickOnRecord(companyName);
		clickDeleteRecord();
		clickConfirmRecordDeletion();
		verifyRecordDeleteMessage(expectedMessage);
		navigateToCompanies();
		verifyRecordDeleted(companyName);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Company "+companyName+" deleted successfully");
	}
}
