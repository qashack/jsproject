package com.application.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Init {
	XmlSuite suite;
	
	public static void main(String[] args) {
		Init init = new Init();		
		init.deleteExistingTestSuite();
		init.generateTestSuite();
		init.createSuiteXmlFile();	
		
	}

	public void deleteExistingTestSuite(){
		File file = new File("testng.xml");
		file.delete();
	}
	public void generateTestSuite(){			
		String filePath = "config/config.properties";
		String configXlFilePath = "config/config.xlsx";
		int modulesCount = ExcelLibrary.getExcelRowCount(configXlFilePath,"Scripts Master");
		suite = new XmlSuite();		
		suite.setName(GenericUtils.getConfigProperties(filePath,"AUTOMATION_SUITE_NAME"));		
		for(int i=1;i<=modulesCount;i++){			
			if(ExcelLibrary.getExcelData(configXlFilePath,"Scripts Master", i, 1).equals("yes")){
				Map<String, String> params = new HashMap<String, String>();
				params.put("Category", ExcelLibrary.getExcelData(configXlFilePath,"Scripts Master", i, 0));
				params.put("Machine", ExcelLibrary.getExcelData(configXlFilePath,"Scripts Master", i, 2));
				params.put("Browser", ExcelLibrary.getExcelData(configXlFilePath,"Scripts Master", i, 3));
				XmlTest test = new XmlTest(suite);
				test.setParameters(params);
				String moduleName = ExcelLibrary.getExcelData(configXlFilePath,"Scripts Master", i, 0);
				test.setName(moduleName);
				int scriptsCount = ExcelLibrary.getExcelRowCount(configXlFilePath, moduleName);
				List<XmlClass> classes = new ArrayList<XmlClass>();
				for(int j=1;j<=scriptsCount;j++){
					if(ExcelLibrary.getExcelData(configXlFilePath, moduleName, j, 1).equals("yes")){
						classes.add(new XmlClass(ExcelLibrary.getExcelData(configXlFilePath, moduleName, j, 0)));
						test.setXmlClasses(classes);
					}
				}
			}
		}
	}
	public void createSuiteXmlFile(){
		Charset utf8 = StandardCharsets.UTF_8;
		try {
			Files.write(Paths.get("testng.xml"), suite.toXml().getBytes(utf8), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(suite.toXml());
	}
	
	public String getConfigProperties(String key){
		String retVal=null;
		FileInputStream fis;
		try {
			fis = new FileInputStream("config/config.properties");
			Properties prop = new Properties();
			prop.load(fis);
			retVal = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		return retVal;
	}
}
