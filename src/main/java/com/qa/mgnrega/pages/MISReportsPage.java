package com.qa.mgnrega.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.mgnrega.base.TestBase;

public class MISReportsPage extends TestBase {

	
	public MISReportsPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String validateMISReportsPageTitle() {
		
		System.out.println(driver.getTitle());
		
		return driver.getTitle();
	}
	
	
}
