package com.application.tests;

import org.testng.annotations.Test;

import com.application.employeepages.EmployeesPage;
import com.application.generics.LoginPage;
import com.application.libraries.ExcelLibrary;
import com.application.pages.DashboardPage;

public class DeleteEmployeeTest extends BaseClass{

	@Test(description="This test deletes an employee")
	public void testDeleteEmployee(){
		String un = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 0);
		String pw = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 1);
		String empName = ExcelLibrary.getExcelData(dataFilePath, "DeleteEmployeeTest", 1, 0);
		LoginPage lp = new LoginPage(driver);
		lp.login(un,pw);
		DashboardPage dp = new DashboardPage(driver);
		dp.navigateToEmployees();
		EmployeesPage ep = new EmployeesPage(driver);
		ep.deleteEmployee(empName);
		ep.verifyEmployeeDeleted(empName, "Record has been deleted successfully");
		ep.signout();
	}
	

}
