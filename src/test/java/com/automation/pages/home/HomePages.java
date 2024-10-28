package com.automation.pages.home;

import org.openqa.selenium.WebDriver;

import com.automation.pages.BasePage;

public class HomePages extends BasePage {
	
	public HomePages(WebDriver driver) {
		super(driver);
	}
	public String getTitle() {
		waitForTitleToBepresent(30, "Home Page ~ Salesforce - Developer Edition", "title");
		String ActualText = driver.getTitle();
		return ActualText;
	}
	
}
