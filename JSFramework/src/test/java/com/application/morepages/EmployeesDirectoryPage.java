package com.application.morepages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.generics.BasePage;
import com.application.libraries.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class EmployeesDirectoryPage extends BasePage{

	WebDriver driver;
	
	public EmployeesDirectoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyEmployeePresent(String employeeName){
		Assert.assertEquals(driver.findElements(By.xpath("//span[text()='"+employeeName+"']")).size(), 1);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Employee "+employeeName+" exists");
	}
}
