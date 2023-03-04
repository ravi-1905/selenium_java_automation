package com.demo.locators;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.demo.base.Constants;
import com.demo.utils.Utils;

public class LoginPageLocators {

	public WebDriver driver;
	public Properties props = Utils.loadConfig(Constants.LOGIN_PAGE_FILE_PATH);

	public LoginPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	// All objects should be defined here
	public String userName = props.getProperty("loginPage_userName");
	public String password = props.getProperty("loginPage_password");
	public String loginBtn = props.getProperty("loginPage_loginBtn");
	public String profileName = props.getProperty("loginPage_profileName");
	public String loginErrorMsg = props.getProperty("loginpage_loginErrorMsg");
	
	public String searchField = props.getProperty("homepage_searchField");
	public String searchBtn = props.getProperty("homepage_searchBtn");
}
