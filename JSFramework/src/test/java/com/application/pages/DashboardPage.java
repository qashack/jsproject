package com.application.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.application.generics.BasePage;
import com.application.libraries.ExtentTestManager;
import com.application.libraries.GenericUtils;
import com.relevantcodes.extentreports.LogStatus;

public class DashboardPage extends BasePage{

	WebDriver driver;
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="divTabs_1")
	private WebElement hrdashboardTab;
	
	@FindBy(xpath="//td[text()='Male Employees']/following-sibling::td[1]")
	private WebElement maleEmployeeCountCell;
	
	@FindBy(xpath="//td[text()='Female Employees']/following-sibling::td[1]")
	private WebElement femaleEmployeeCountCell;
	
	@FindBy(css="#highcharts-0 .highcharts-series > path")
	private List<WebElement> genderPies;
	
	@FindBy(css="#highcharts-0 .highcharts-tooltip>text>tspan:nth-child(2)")
	private WebElement genderTooltip;
	
	public void navigateToHrDashboardTab(){
		hrdashboardTab.click();
	}
	
	public void verifyMaleEmployeeCount(int males){
		String maleEmpCount=maleEmployeeCountCell.getText();		
		Assert.assertEquals(Integer.parseInt(maleEmpCount), males);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Male Employee count:"+maleEmpCount+" matches expected count:"+males);
	}
	
	
	public void verifyFemaleEmployeeCount(int females){
		String femaleEmpCount=femaleEmployeeCountCell.getText();		
		Assert.assertEquals(Integer.parseInt(femaleEmpCount), females);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Female Employee count:"+femaleEmpCount+" matches expected count:"+females);
	}
	
	public void verifyGenderGraph(int male, int female){		
		for(int i=0;i<genderPies.size();i++){
			GenericUtils.mouseOver(driver,genderPies.get(i));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int actualGender = Integer.parseInt(genderTooltip.getText().replace(": ", ""));
			if(i==0){
				if(actualGender==male){
					ExtentTestManager.getTest().log(LogStatus.PASS, "Male Employee count in graph matches expected count:"+male);
				}
				else{					
					Assert.fail("Male Employee count in graph:"+actualGender+" does not match expected count:"+male);
				}								
			}
			else{
				if(actualGender==female){
					ExtentTestManager.getTest().log(LogStatus.PASS, "Female Employee count in graph matches expected count:"+female);
				}
				else{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Female Employee count in graph:"+actualGender+" does not match expected count:"+female);
					Assert.fail("Female Employee count in graph:"+actualGender+" does not match expected count:"+female);
				}								
			}			
		}
	}
	
}
