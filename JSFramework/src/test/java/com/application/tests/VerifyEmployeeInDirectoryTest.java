package com.application.tests;

import org.testng.annotations.Test;

import com.application.generics.LoginPage;
import com.application.libraries.ExcelLibrary;
import com.application.morepages.EmployeesDirectoryPage;
import com.application.pages.DashboardPage;

public class VerifyEmployeeInDirectoryTest extends BaseClass{

	@Test(description = "This test case verifies created employee in directory")
	public void testVerifyEmployeeInDirectory(){
		String un = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 0);
		String pw = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 1);
		String firstName = ExcelLibrary.getExcelData(dataFilePath, "VerifyEmployeeInDirectoryTest", 1, 0);
		String lastName = ExcelLibrary.getExcelData(dataFilePath, "VerifyEmployeeInDirectoryTest", 1, 1);
		LoginPage lp = new LoginPage(driver);
		lp.login(un,pw);
		DashboardPage dp = new DashboardPage(driver);
		dp.navigateToEmployeesDirectory();
		EmployeesDirectoryPage edp = new EmployeesDirectoryPage(driver);
		edp.verifyEmployeePresent(firstName+" "+lastName);
		edp.signout();
	}
}
