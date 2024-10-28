package com.automation.pages.login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.automation.pages.BasePage;

public class LoginPage extends BasePage {
	//WebDriver driver;
	
	@FindBy(id="username") WebElement UserNameEle;
	
	@FindBy(id="password") WebElement passwordEle;
	
	@FindBy(xpath="//*[@id=\"rememberUn\"]")  WebElement rememeberMeEle;
	
	@FindBy(id="Login")  WebElement loginButtonEle;
	
	@FindBy(id="error") WebElement Errormessage;
	 
	
	
	
	//when we create object of the class, we need to inform INITIALIZE those locator as well. for that we need to use constructor of the class
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String data)
	{
		enterText(UserNameEle, data, "Username");
	
	}
	
	public void enterPassword(String data)
	{
		enterText(passwordEle, data, "Password");
	}
	
	
	public void clickRememberMe()
	{
		
		clickElement(rememeberMeEle, "Remember Me box");
		
	}
	
	public WebDriver  ClickLogin()
	{
		waitForElementToBeClicked(loginButtonEle, 30, "clickLogin");
		clickElement(loginButtonEle, "Login button");
		return driver;
		
		
	}
	
	
	
	public String actualErrorMessage() {
		waitFortextToBePresent(Errormessage, 30, "Please enter your password.", "Error message");
		return getTextFromWebElement(Errormessage, "Error Message");
		
	}
	
	
	
}



