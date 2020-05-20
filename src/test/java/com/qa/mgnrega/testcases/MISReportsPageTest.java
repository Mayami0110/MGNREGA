package com.qa.mgnrega.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.mgnrega.base.TestBase;
import com.qa.mgnrega.pages.MISReportsPage;
import com.qa.mgnrega.pages.WebPortalPage;
import com.qa.mgnrega.util.SendEmailToWithAttachement;
import com.qa.mgnrega.util.TestUtil;

public class MISReportsPageTest extends TestBase {

	WebPortalPage webportalPage;
	MISReportsPage misReportPage;
	TestUtil testutil = new TestUtil();
	String finalStatus = "";
	
	String strWebPortalExecutionTime = "";
	 String  strMisReportExecutionTime ="";
	private  String strMisReportStartTime = null;
	private  String strMisReportStopTime = null;	
	 float strMisReporttimeElapsed ;
	
	 String webPortalURL,misReportURL;

	public MISReportsPageTest() {
		super();
	}

	@BeforeTest
	public void setup() {
		
		try {
		
		strWebPortalExecutionTime = initializations();

		webportalPage = new WebPortalPage();

		//misReportPage = webportalPage.doClickOnMISReports();
	
		/*getScreenshot("WebPortalPage");
		
		strMisReportStartTime = testutil.start();
				
		misReportPage = webportalPage.doClickOnMISReports();
		
		System.out.println(misReportPage);*/
		

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	
	@Test(priority = 0)
	public void openWebPortalPageTest() {

	
		try {
		
		//getScreenshot("WebPortalPage");
			
			// webPortalURL = webportalPage.CurrentURL();
		
		strMisReportStartTime = testutil.start();
				
		misReportPage = webportalPage.doClickOnMISReports();
		
		
		String misReportPageTitle = misReportPage.validateMISReportsPageTitle();
		
		if(misReportPageTitle.equalsIgnoreCase(prop.getProperty("misReportsPageTitle")))
				
				finalStatus = "Working";
				
		else finalStatus = " Not Working";
		
	/*	Assert.assertEquals(misReportPageTitle, prop.getProperty("misReportsPageTitle"),
				"MIS Report Title is not Matching");
	*/	
/*		getScreenshot("MISReportPage");
*/		
		//misReportURL = webportalPage.CurrentURL();
		
		strMisReportStopTime = testutil.stop();

		strMisReporttimeElapsed = testutil.getElapsedTime();

		strMisReportExecutionTime = testutil.ExecutionTime(strMisReporttimeElapsed);

		getScreenshot("MISReportPage");
		
		
	}
	catch(Exception e)
	{
		System.out.println(e);
		
		finalStatus = " Not Working";
		
/*		getScreenshot("MISReportPage");
*/		
		strMisReportStopTime = testutil.stop();

		strMisReporttimeElapsed = testutil.getElapsedTime();

		strMisReportExecutionTime = testutil.ExecutionTime(strMisReporttimeElapsed);

		getScreenshot("MISReportPage");

	
	}
		

	}
	
	
	
	@Test(priority=1)
	public void sendEmailToTeam() {
		
		SendEmailToWithAttachement sendEmailtTo = new SendEmailToWithAttachement();
		sendEmailtTo.SendMailMethod(strWebPortalExecutionTime, strMisReportExecutionTime ,finalStatus);
				
	}
	
	@AfterTest
	public void teardown() {

		driver.quit();
	}

}
