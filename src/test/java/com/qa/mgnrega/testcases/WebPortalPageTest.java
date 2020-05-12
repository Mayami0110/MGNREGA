package com.qa.mgnrega.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.mgnrega.base.TestBase;
import com.qa.mgnrega.pages.MISReportsPage;
import com.qa.mgnrega.pages.WebPortalPage;

public class WebPortalPageTest extends TestBase {

	WebPortalPage webportalPage;
	MISReportsPage misReportPage;

	public WebPortalPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initializations();

		webportalPage = new WebPortalPage();
	}

	@AfterMethod
	public void teardown() {

		driver.quit();
	}

//	@Test(priority = 0)
	public void openWebPortalPageTest() {

		String title = webportalPage.validateWebPortalPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, prop.getProperty("webPortalPageTitle"));

	}

	//@Test(priority = 1)
	public void navigationToMISReports() {

		misReportPage = webportalPage.doClickOnMISReports();
	//	Assert.assertEquals(title, prop.getProperty("loginTitle"));

	}

}
