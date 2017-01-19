package com.application.tests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.application.employeepages.EmployeesPage;
import com.application.generics.LoginPage;
import com.application.libraries.ExcelLibrary;
import com.application.pages.DashboardPage;

public class CreateEmployeeTest extends BaseClass{

	@Test(description="This test case is to create a new employee")
	public void testCreateEmployee(){
		String un = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 0);
		String pw = ExcelLibrary.getExcelData(dataFilePath, "Login", 1, 1);
		ArrayList<String> dataList = ExcelLibrary.getExcelRowData(dataFilePath, "CreateEmployeeTest", 1);
		LoginPage lp = new LoginPage(driver);
		lp.login(un,pw);
		DashboardPage dp = new DashboardPage(driver);
		dp.navigateToEmployees();
		EmployeesPage ep = new EmployeesPage(driver);
		ep.addNewEmployee(dataList.get(0), dataList.get(1), dataList.get(2), dataList.get(3), dataList.get(4), dataList.get(5));
		ep.verifyEmployeeCreated(dataList.get(3)+" "+dataList.get(4), "Record has been added successfully");				
		ep.signout();		
	}
}
