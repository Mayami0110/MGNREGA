package com.qa.mgnrega.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.mgnrega.base.TestBase;

public class WebPortalPage extends TestBase {

	@FindBy(xpath = "//*[@id=\"myslidemenu\"]/ul/li/a[contains(text(),'MIS Reports')]")
	////*[@id="myslidemenu"]/ul/li/a[contains(text(),'MIS Reports')]
	WebElement linkmisreports;

	
	public WebPortalPage() {
		
		PageFactory.initElements(driver, this);

	}

	// Actions
	public String validateWebPortalPageTitle() {
		
		
		
		System.out.println(driver.getTitle());
		
		return driver.getTitle();
	}
	


	public MISReportsPage doClickOnMISReports() {

		linkmisreports.click();
		
		return new MISReportsPage();

	}

	public String CurrentURL() {

		System.out.println(driver.getCurrentUrl());
		
		return driver.getCurrentUrl();
	}

}
	