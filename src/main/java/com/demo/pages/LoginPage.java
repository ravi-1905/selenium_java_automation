package com.demo.pages;

import org.openqa.selenium.WebDriver;

import com.demo.locators.LoginPageLocators;
import com.demo.pageactions.PageActions;
import com.demo.utils.Waits;
import com.demo.validations.Validations;

public class LoginPage extends PageActions {
	
	public WebDriver driver;
	public LoginPageLocators loginLocators;
	public Validations verify;
	public Waits wait;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		loginLocators = new LoginPageLocators(driver);
		verify = new Validations();
		wait = new Waits(driver);
	}
	
	public void loginToApplication(String sUserName, String sPassword) {
		enterText(loginLocators.userName, sUserName);
		enterText(loginLocators.password, sPassword);
		clickOnElement(loginLocators.loginBtn);
		
		wait.waitForElementToVisible(loginLocators.profileName, 20);
		String profileName = getInnerText(loginLocators.profileName);
		verify.verifyText(profileName, "jonsnow");
	}
	
	public void loginToApplicationWithInvalidCredentials(String sUserName, String sPassword, String expectedErrorText) {
		enterText(loginLocators.userName, sUserName);
		enterText(loginLocators.password, sPassword);
		clickOnElement(loginLocators.loginBtn);
		
		wait.waitForElementToVisible(loginLocators.loginErrorMsg, 20);
		String errorMsg = getInnerText(loginLocators.loginErrorMsg);
		verify.verifyText(errorMsg, expectedErrorText);
	}
	
	public void searchTest(String inputText) {
//		clickOnElement(loginLocators.searchField);
		wait.waitForElementToClickable(loginLocators.searchField, 20);
		enterText(loginLocators.searchField, inputText);
		clickOnElement(loginLocators.searchBtn);
	}

}
