package com.automation.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertyFileUtility;
import com.google.common.io.Files;

import LogTests.LogTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver = null;
	
	protected Logger mylog = LogManager.getLogger(BaseTest.class);
	
	/// private Logger mylog=LogManager.getLogger(BaseTest.class);
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) {
		mylog.info("*********************@BeforeMethod********************* ");
		launchBrowser("chrome");
		maximizeScreen();
		String url = PropertyFileUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
		goToUrl(url);
		
	}

	@AfterMethod
	public void tearDownMethod() {
		mylog.info("*********************@teardown method********************* ");
		closeDriver();
	}

	
	public void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			break;
		}
	}

	public void goToUrl(String url) {
		driver.get(url);
		mylog.info(url + "is entered");

	}

	public void closeDriver() {
		driver.close();
		mylog.info("driver closed");
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
	public void maximizeScreen() {
		driver.manage().window().maximize();

	}
	public void takeScreenshot(String filepath) {

		TakesScreenshot screenCapture = (TakesScreenshot) driver;// typcasting webdriver driver to takescreenshot type
		File src = screenCapture.getScreenshotAs(OutputType.FILE);// screenshot
		File destFile = new File(filepath);
		try {
			Files.copy(src, destFile);
			mylog.info("screen captured");
		} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
			mylog.error("problem occured during screenshot taking");
		}
	}

	public void takePartialScreenshot(WebElement ele, String filepath) {
		File src = ele.getScreenshotAs(OutputType.FILE); // getScreenshotAs method is used directely to take the
															// screenshot of webelement.
		File destFile = new File(filepath);
		try {
			Files.copy(src, destFile);
			mylog.info("screen captured");
		} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
			mylog.error("problem occured during screenshot taking");
		}
	}
}
