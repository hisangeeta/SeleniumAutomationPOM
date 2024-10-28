package com.automation.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.home.HomePages;
import com.automation.pages.login.LoginPage;
import com.automation.utility.Constants;
import com.automation.utility.PropertyFileUtility;
import com.automation.utility.XLUtility;
//login page and home page is needed for this page
public class AutomationScripts extends BaseTest {
	//private Logger mylog=LogManager.getLogger(AutomationScripts.class);
	

	@Test
	public void invalid_password_login() {
		String username = PropertyFileUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword("");
		
		loginPage.ClickLogin();

		
		String expected_ErrorMessage = "Please enter your password.";
		String actual_ErrorMessage=loginPage.actualErrorMessage();
		Assert.assertEquals(actual_ErrorMessage, expected_ErrorMessage);

		
		mylog.info("actual message is same as expected");
		//extentReportUtility.logTestInfo("actual message is same as expected");

	}

	/*
	 * @DataProvider(name="logindata") public Object[][] readData(){
	 * 
	 * 
	 * return
	 * XLUtility.readAllDataFromXLToArray(Constants.Salesforce_XLDATA,"Sheet1");
	 * 
	 * }
	 * 
	 * @Test(dataProvider="logindata")
	 * public void valid_login(String username, String password  ) {

	
		
		
		mylog.info("username:" + username +"password:" +password);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickRememberMe();
		driver=	loginPage.ClickLogin();
		
	 */
	@Test
	public void valid_login() {

	
		String username = PropertyFileUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
		String password = PropertyFileUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");
		
		
		mylog.info("username:" + username +"password:" +password);
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickRememberMe();
		driver=	loginPage.ClickLogin();
		
		
		
		HomePages homepage=new HomePages(driver);
		String ExpectedPageTitle = "Home Page ~ Salesforce - Developer Edition";
		String ActualPageTitle =homepage.getTitle();
		
		try {
			Assert.assertEquals(ActualPageTitle, ExpectedPageTitle);
			//extentReportUtility.logTestInfo("Actual title is same as expected so test case passed");
			
		} catch (Throwable e) {
			String filename=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
			takeScreenshot(Constants.SCREENSHOTS_DIRECTORY_PATH+filename+".png");
			Assert.fail("Actual text is" + ActualPageTitle + "and expected test is" + ExpectedPageTitle);
			//extentReportUtility.logTestFailedWithException(e);
		}
	}
	
}