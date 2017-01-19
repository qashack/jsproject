package com.application.organizationpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.generics.BasePage;
import com.application.libraries.JavascriptLibrary;

public class OrganizationPage extends BasePage{

	WebDriver driver;
		
	@FindBy(css="div[title='Companies']")
	private WebElement companiesIcon;
	
	public OrganizationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickCompaniesIcon(){
		JavascriptLibrary.javascriptScrollWindow(driver, 0, 0);
		companiesIcon.click();
	}
	
	
}
