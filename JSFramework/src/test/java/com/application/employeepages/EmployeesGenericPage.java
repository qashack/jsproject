package com.application.employeepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.application.generics.BasePage;
import com.application.libraries.JavascriptLibrary;

public class EmployeesGenericPage extends BasePage{

	WebDriver driver;
	public EmployeesGenericPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[title='Employees']")
	private WebElement employeesIcon;
	
	public void clickEmployeesIcon(){
		JavascriptLibrary.javascriptScrollWindow(driver, 0, 0);
		employeesIcon.click();
	}
}
