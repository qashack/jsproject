package com.application.libraries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.application.tests.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

public class MyEventListener implements WebDriverEventListener {
	String configFilePath = "config/config.properties";
	long delay = Long.parseLong(GenericUtils.getConfigProperties(configFilePath, "CUSTOM_STEP_DELAY"));
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub		
		
	}
	
	public void afterNavigateTo(String url, WebDriver driver)
	{
		// TODO Auto-generated method stub		
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) 
	{
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver)
	{
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver)
	{
		try {			
			JavascriptLibrary.javascriptScrollWindow(driver, element.getLocation().getX(), element.getLocation().getY()-100);
			//extentTest.log(LogStatus.INFO, "clicking on ["+element.toString().split("->")[1]);
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void afterClickOn(WebElement element, WebDriver driver)
	{
		String trace="";
		StackTraceElement[] arr = Thread.currentThread().getStackTrace();
		for(int i=0;i<arr.length;i++){
			//System.out.println(arr[i]);
			if(arr[i].getClassName().contains("com.application.tests")){
				trace = "[class name: "+arr[i].getClassName()+" Method name: "+arr[i].getMethodName()+" line number: "+arr[i].getLineNumber()+"]";
			}
			else if(arr[i].getClassName().contains("com.application") && !arr[i].getClassName().contains("com.application.tests")){
				trace = trace+"[class name: "+arr[i].getClassName()+" Method name: "+arr[i].getMethodName()+" line number: "+arr[i].getLineNumber()+"]";
			}
		}
		try {
			ExtentTestManager.getTest().log(LogStatus.INFO,"clicked on ["+element.toString().split("-> ")[1]+" <span style='color:#C70039'>"+trace+"</span>");	
			BaseClass.br.append("clicked on ["+element.toString().split("-> ")[1]+"\r\n");
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) 
	{
		//extentTest.log(LogStatus.INFO,"Typing into ["+element.toString().split("->")[1]);
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		String trace="";
		StackTraceElement[] arr = Thread.currentThread().getStackTrace();
		for(int i=0;i<arr.length;i++){
			//System.out.println(arr[i]);
			if(arr[i].getClassName().contains("com.application.tests")){
				trace = "[class name: "+arr[i].getClassName()+" Method name: "+arr[i].getMethodName()+" line number: "+arr[i].getLineNumber()+"]";
			}
			else if(arr[i].getClassName().contains("com.application") && !arr[i].getClassName().contains("com.application.tests")){
				trace = trace+"[class name: "+arr[i].getClassName()+" Method name: "+arr[i].getMethodName()+" line number: "+arr[i].getLineNumber()+"]";
			}
		}
		ExtentTestManager.getTest().log(LogStatus.INFO,"HTML","Typed <span style='color:#bbbbbb'>"+element.getAttribute("value")+"</span> into ["+element.toString().split("-> ")[1]+" <span style='color:#C70039'>"+trace+"</span>");
		BaseClass.br.append("Typed "+element.getAttribute("value")+" into ["+element.toString().split("-> ")[1]+"\r\n");
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		//extentTest.log(LogStatus.ERROR,"There is an error. The message is "+throwable.getMessage());		
	}

	
	
}
