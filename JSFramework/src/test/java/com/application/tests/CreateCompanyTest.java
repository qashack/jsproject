package com.application.tests;

import org.testng.annotations.Test;

import com.application.generics.LoginPage;
import com.application.libraries.ExcelLibrary;
import com.application.organizationpages.CompaniesPage;
import com.application.pages.DashboardPage;

public class CreateCompanyTest extends BaseClass{
	
	@Test(description="This test case is to create a new company")
	public void testMethod(){
		String un = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 0);
		String pw = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 1);
		LoginPage lp = new LoginPage(driver);
		lp.login(un,pw);
		DashboardPage dp = new DashboardPage(driver);
		dp.navigateToCompanies();
		CompaniesPage cp = new CompaniesPage(driver);
		cp.addNewCompany("New Company");
		cp.verifyCompanyCreated("New Company");
		cp.signout();
	}
}
