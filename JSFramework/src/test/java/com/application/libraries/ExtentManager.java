package com.application.libraries;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	static ExtentReports extent;
    static String filePath;
    static String timeStamp;
    public static String reportsFolderName;
    public static String screenshotFolderName;
    public static void generateFilePath() {
    	timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date());
    	reportsFolderName = "reports/Report_"+timeStamp;
    	screenshotFolderName = reportsFolderName+"/screenshots";
    	try {
			FileUtils.forceMkdir(new File(reportsFolderName));
			FileUtils.forceMkdir(new File(screenshotFolderName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	filePath=reportsFolderName+"/Reports.html";    	
	}
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
            //extent.x();
            extent.loadConfig(new File("config/extent-config.xml"));	
            
        }
        
        return extent;
    }
}
