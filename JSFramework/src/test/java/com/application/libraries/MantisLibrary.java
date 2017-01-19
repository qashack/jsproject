package com.application.libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import org.apache.commons.codec.binary.Base64;

import biz.futureware.mantis.rpc.soap.client.AttachmentData;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;

// TODO: Auto-generated Javadoc
/**
 * The Class MantisLibrary.
 */
public class MantisLibrary {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws MalformedURLException the malformed url exception
	 * @throws ServiceException the service exception
	 * @throws RemoteException the remote exception
	 */
	public static void main(String[] args) throws MalformedURLException, ServiceException, RemoteException {
		 
		 
		 // Demo 01
		 // Get a bug by bug_id
		// IssueData aIssueData = portType.mc_issue_get (user, pwd, new BigInteger ("0000107"));
		 //System.out.println (aIssueData.getId());
		 //System.out.println (aIssueData.getSummary ());
		 
		 /*
		 // Demo 02
		 // Get the account of a project.
		 AccountData [] users = portType.mc_project_get_users (user, pwd, project_id, new BigInteger ("1"));
		 for (AccountData ad: users) {
			 System.out.println (ad.getName ());
		 }
		
		 // Demo 03
		 // Get the issue headers of a project.
		 IssueHeaderData [] headers = portType.mc_project_get_issue_headers (user, pwd, project_id, BigInteger.valueOf (1), BigInteger.valueOf (50));
		 for (IssueHeaderData header: headers) {
			 System.out.println (header.getId () + "\t" + header.getSummary ());
		 }

		 // Demo 04
		 // Get the issues of a project.
		 IssueData [] issues = portType.mc_project_get_issues (user, pwd, project_id, BigInteger.valueOf (1), BigInteger.valueOf (10));
		 for (IssueData issueData: issues) {
			 System.out.println (issueData.getId () + "\t" + issueData.getStatus().getName () + "\t" + issueData.getSummary ());
		 }
		 */
	}
	
	public static void logDefectToMantis(String summary, String description, String steps, String attachmentFile){
		String user = "administrator";
		String pwd = "admin@123";
		URL url;
		try {
			url = new URL ("http://localhost/mantisbt/api/soap/mantisconnect.php");
			MantisConnectLocator mcl = new MantisConnectLocator ();
			MantisConnectPortType portType = mcl.getMantisConnectPort (url);
		
			BigInteger project_id = new BigInteger ("1");		
			 
			ObjectRef project = new ObjectRef();
			project.setId(project_id);
			 
			IssueData issue = new IssueData();
			issue.setProject(project);
			
			issue.setCategory("Automation");
			issue.setSummary(summary);
			issue.setDescription(description);
			issue.setSteps_to_reproduce(steps);			
			
			//Calendar date_submitted;
			//AttachmentData attachmentData = new AttachmentData();
			//attachmentData.setFilename(attachmentFile);			
			//AttachmentData[] at = new AttachmentData[5];
			//at[0]=attachmentData;
			//issue.setAttachments(at);
			BigInteger issueId = portType.mc_issue_add(user, pwd, issue);
			//portType.mc_issue_attachment_add(user, pwd, issueId, "Some Name", "Image", encodeFileToBase64Binary(new File(attachmentFile)));
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		 
	}

	public static byte[] encodeFileToBase64Binary(File file){
	    byte[] encodedfile = null;
	    try {
	        FileInputStream fileInputStreamReader = new FileInputStream(file);
	        byte[] bytes = new byte[(int)file.length()];
	        fileInputStreamReader.read(bytes);
	        encodedfile = Base64.encodeBase64(bytes);
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch <span id="IL_AD2" class="IL_AD">block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	     
	    return encodedfile;
	}
	
}
