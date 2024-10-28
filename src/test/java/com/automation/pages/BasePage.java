package com.automation.pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.automation.utility.ExtentReportsUtility;

public class BasePage {
	
	
	protected WebDriver driver;
	private WebDriverWait wait = null;
	private Logger mylog=LogManager.getLogger(BasePage.class);
	protected ExtentReportsUtility extentReportUtility = ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			mylog.info("data is entered in the " + objectName);
			extentReportUtility.logTestInfo("data is entered in the " + objectName);
		} else {
			mylog.error(objectName + " textbox is not diplayed");
			extentReportUtility.logTestFailed(objectName + " textbox is not diplayed");
		}
	}

	public void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			mylog.info(objectName + " button is clicked");
			extentReportUtility.logTestInfo(objectName + " button is clicked");
		} else {
			mylog.error(objectName + " button is not diplayed");
			extentReportUtility.logTestFailed(objectName + " button is not diplayed");
		}
	}

	public void selectElement(WebElement ele, String objectName) {
		if (!ele.isSelected()) {
			ele.click();
			mylog.info(objectName + " button is selected");
			extentReportUtility.logTestInfo(objectName + " button is selected");
		} else {
			mylog.error(objectName + " button is already selected");
			extentReportUtility.logTestFailed(objectName + " button is already selected");
		}

	}

	public String selectByValue(WebElement ele, String value) {
		Select select = new Select(ele);
		// select.deselectAll();
		select.selectByValue(value);
		mylog.info(value+"is selected");
		extentReportUtility.logTestInfo(value+"is selected");
		return value;
		
	}

	public int selectByIndex(WebElement ele, int value) {
		Select select = new Select(ele);
		// select.deselectAll();
		
		select.selectByIndex(value);
		mylog.info(value+"is selected");
		extentReportUtility.logTestInfo(value+"is selected");
		return value;
	}

	public String selectByVisibleText(WebElement ele, String value) {
		Select select = new Select(ele);
		// select.deselectAll();
		select.selectByVisibleText(value);
		mylog.info(value+"is selected");
		extentReportUtility.logTestInfo(value+"is selected");

		return value;
	}

	

	public Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		mylog.info("The curser is moved switched to alert");
		extentReportUtility.logTestInfo("The curser is moved switched to alert");
		return alert;
	}

	public void alertGetText(Alert alert, String objectname) {
		mylog.info("Getting the text in the " + objectname + "alert");
		String text = alert.getText();
		mylog.info("Alert text is " + text);
		extentReportUtility.logTestInfo("Alert text is " + text);
	}

	public void acceptAlert(Alert alert, String objectname) {
		alert.accept();
		mylog.info("alert" + objectname + "has been accepted");
	}

	public void dismissAlert(Alert alert, String objectname) {
		alert.dismiss();
		mylog.info("alert " + objectname + "has been accepted");
	}

	public String getTextFromWebElement(WebElement ele, String objectName){
		
		
		String text = null;
		if (ele.isDisplayed()) {
			text = ele.getText();
			mylog.info("text is extracted from " + objectName);
		} else {
			mylog.error("text is not extracted from " + objectName);
		}

		return text;

	}

	public void mouseOverElement(WebElement ele, String objectName) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		mylog.info("curser moved to element = " + objectName);
	}

	

	public void waitForVisibility(WebElement ele, long timeInsec, String objectName) {
		mylog.info(objectName + "waiting for visibility for maximum of " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void waitForAlertToPresent(long timeInsec, String objectName) {
		mylog.info(objectName + "waiting for Alert to be present " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForElementToBeClicked(WebElement ele, long timeInsec, String objectName) {
		mylog.info(objectName + "waiting for elemnt to be clicked " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitFortextToBePresent(WebElement ele, long timeInsec, String text, String objectName) {
		mylog.info(objectName + "waiting for string to be presend " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
	}

	public void waitForWindowtToBePresent(long timeInsec, int numOfWindows, String objectName) {
		mylog.info(objectName + "waiting to the windows to be present " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.numberOfWindowsToBe(numOfWindows));
		
	}
	
	public void waitForTitleToBepresent(long timeInsec, String title, String objectName) {
		mylog.info(objectName + "waiting to the title to be present " + timeInsec + "sec");
		wait = new WebDriverWait(driver, timeInsec);
		wait.until(ExpectedConditions.titleIs(title));
	}
	

	public void implicitWait(long timeInsec) {
		driver.manage().timeouts().implicitlyWait(timeInsec, TimeUnit.SECONDS);
	}

}
