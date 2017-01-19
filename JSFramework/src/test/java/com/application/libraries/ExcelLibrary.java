package com.application.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {
	public static String getExcelData(String filePath, String sheetName, int rowNo, int cellNo)
	{
		try
		{
			
			File f = new File(filePath);
			FileInputStream fileInput = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fileInput);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowNo);
			Cell c = r.getCell(cellNo);
			String data = c.getStringCellValue();
			return data;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return " ";
		}
	}
	
	public static void writeExcelData(String filePath, String sheetName, int rowNo, int cellNo, String data)
	{
		try
		{
			FileInputStream fileInput = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fileInput);
			wb.createSheet();
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowNo);
			if (r == null)
		        r = st.createRow(rowNo);
			Cell c = r.createCell(cellNo);
			if (c == null)
		        c = r.createCell(cellNo);
			c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(data);
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);				
		}
		catch(Exception e)
		{
			
		}
	}
	public static int getExcelRowCount(String filePath, String sheetName)
	{
		int rowNo = 0;
		try
		{
			FileInputStream fileInput = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fileInput);
			Sheet st = wb.getSheet(sheetName);
			rowNo = st.getLastRowNum();
			
		}
		catch(Exception e)
		{
			
		}
		return rowNo;
	}
		
	public static int getExcelCellCount(String filePath, String sheetName, int rowNo)
	{
		try
		{
			FileInputStream fileInput = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fileInput);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowNo);
			return r.getLastCellNum();
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public static ArrayList<String> getExcelRowData(String filePath, String sheetName, int rowNum){
		ArrayList<String> list = new ArrayList<String>();
		try
		{			
			File f = new File(filePath);
			FileInputStream fileInput = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fileInput);
			Sheet st = wb.getSheet(sheetName);			
			Row r = st.getRow(rowNum);
			int cellCount = getExcelCellCount(filePath,sheetName,rowNum);
			for(int i=0;i<cellCount;i++){
				list.add(r.getCell(i).getStringCellValue());
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
		return list;
	}
}








