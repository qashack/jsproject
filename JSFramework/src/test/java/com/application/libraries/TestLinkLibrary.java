package com.application.libraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestLinkLibrary {
		public static String DEVKEY="7aa8c9ed62b175de7629a169d74253a7";

		public static String URL= "http://localhost/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
				
		public static void setTestLinkInformation(String suite, String testCaseName, char res){
			
			TestLinkAPI api = null;
			try {
				api = new TestLinkAPI(new URL(URL), DEVKEY);
			} catch (TestLinkAPIException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			String testProject="WebHR";	
			String testPlan="AutomationPlan";	
			String testCase=testCaseName;
			
			String notes=null;	
			ExecutionStatus result=null;	
			
			if(res=='p'){
				result = ExecutionStatus.PASSED;		
				notes="Executed successfully";
			}
			else if(res=='f'){
				result=ExecutionStatus.FAILED;	
				notes="Execution failed";
			}
			
			Integer tcId = api.getTestCaseIDByName(testCaseName, suite, testProject, null);
			//TestCase tc = api.getTestCaseByExternalId("webhr-1", null);
			TestPlan tp = api.getTestPlanByName(testPlan, testProject);
			Build build = api.getLatestBuildForTestPlan(tp.getId());
			String bugId=null;
			
			Map<String, String> customFields = null;
			Boolean overwrite = false;
			Boolean guess = false;
			
			Integer platformId = null;
			String platformName = null;
			
			//System.out.println(tc.getPlatform().getId());
			//System.out.println(tc.getPlatform().getName());
			
			//api.reportTCResult(tc.getId(), tc.getId(), tp.getId(), result, build.getId(), "SampleBuild", "Sample notes", guess, bugId, tc.getPlatform().getId(), tc.getPlatform().getName(), customFields, overwrite);
			api.reportTCResult(tcId, tcId, tp.getId(), result, build.getId(), "SampleBuild", "Sample notes", guess, bugId, platformId, platformName, customFields, overwrite);
						

		}
		public static void main(String[] args) {
			setTestLinkInformation("Regression","CreateCompanyTest",'p');
		}

}
