package com.demo.test;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.demo.base.Base;
import com.demo.base.Constants;
import com.demo.pages.LoginPage;
import com.demo.utils.Utils;
import com.demo.validations.Validations;

public class LoginPageTest extends Base {

	public WebDriver driver;
	private Logger log = LogManager.getLogger(LoginPageTest.class.getName());
	public Properties loginTestData = Utils.loadConfig(Constants.LOGIN_PAGE_TEST_DATA);
	LoginPage loginPage;
	public Validations verify;

	@BeforeMethod
	public void initialize() throws IOException, FilloException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		verify = new Validations();
	}

	@Test(enabled = false)
	public void test_login() {
		loginPage.loginToApplication(loginTestData.getProperty("userName"), loginTestData.getProperty("password"));
	}

	@Test(enabled = false)
	public void test_loginWithInvalidCredentials() {
		loginPage.loginToApplicationWithInvalidCredentials(loginTestData.getProperty("invalid_userName"),
				loginTestData.getProperty("invalid_password"), loginTestData.getProperty("expected_errorMsg"));
		
	}
	
	@Test
	public void test_searchTest() {
		loginPage.searchTest(loginTestData.getProperty("searchInput"));
		
	}

	
	@AfterMethod
	public void teardown() {
//		driver.close();
		driver.quit();
		log.info("Driver is closed");
	}
}
