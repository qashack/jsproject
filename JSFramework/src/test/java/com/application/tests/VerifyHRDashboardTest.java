package com.application.tests;

import org.testng.annotations.Test;

import com.application.generics.LoginPage;
import com.application.libraries.ExcelLibrary;
import com.application.pages.DashboardPage;

public class VerifyHRDashboardTest extends BaseClass{

	@Test(description="This test verifies the Dashboard")
	public void testVerifyHRDashboard(){
		String un = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 0);
		String pw = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 1);
		LoginPage lp = new LoginPage(driver);
		lp.login(un,pw);
		DashboardPage dp = new DashboardPage(driver);
		dp.navigateToHrDashboardTab();
		dp.verifyMaleEmployeeCount(2);
		dp.verifyFemaleEmployeeCount(1);
		dp.verifyGenderGraph(2,1);
	}		
}
