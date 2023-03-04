package com.demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.pageactions.PageActions;

public class Waits extends PageActions {
	
	WebDriverWait wait;
	private Logger log = LogManager.getLogger(Waits.class.getName());
	
	public Waits(WebDriver driver) {
		super(driver);
	}
	

	public void waitForElementToVisible(String sLocator, int timeoutInSeconds) {
		try {
			wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(sLocator)));
		} catch (TimeoutException exception) {
			log.info("Unable to find the element on given time: " + exception.getStackTrace());
		}
	}
	
	public void waitForElementToClickable(String sLocator, int timeoutInSeconds) {
		try {
			wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.elementToBeClickable(getLocator(sLocator)));
		} catch (TimeoutException exception) {
			log.info("Unable to click the element on given time: " + exception.getStackTrace());
		}
	}
	
	
	
}
