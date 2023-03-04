package com.demo.pageactions;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.demo.base.Base;

public class PageActions extends Base{

	public WebDriver driver;
	private Logger log = LogManager.getLogger(PageActions.class.getName());

	public PageActions(WebDriver driver) {
		this.driver = driver;
	}

	public By getLocator(String sLocator) {
		String sLocatorType = sLocator.split("~")[0];
		String sLocatorValue = sLocator.split("~")[1];
		By by = null;

		switch (sLocatorType) {
		case "Xpath":
			by = By.xpath(sLocatorValue);
			break;

		case "Id":
			by = By.id(sLocatorValue);
			break;

		case "LinkText":
			by = By.linkText(sLocatorValue);
			break;

		case "Name":
			by = By.name(sLocatorValue);
			break;

		case "Class":
			by = By.className(sLocatorValue);
			break;

		case "CssSelector":
			by = By.cssSelector(sLocatorValue);
			break;

		case "PartialLinkText":
			by = By.partialLinkText(sLocatorValue);
			break;

		default:
			log.info("Invalid sLocator type");
		}

		return by;
	}

	public WebElement findElement(String sLocator) {
		WebElement element = null;
		
		try {
			element = driver.findElement(getLocator(sLocator));
		} catch (NoSuchElementException exception) {
			log.info("Element not found on page: " + exception.getStackTrace());
		}
		return element;
	}
	
	public List<WebElement> findElements(String sLocator) {
		List<WebElement> element = null;

		try {
			element = driver.findElements(getLocator(sLocator));
		} catch (NoSuchElementException exception) {
			log.info("Elements not found on page: " + exception.getStackTrace());
		}
		return element;
	}

	public void clickOnElement(String sLocator) {
		try {
			findElement(sLocator).click();
		} catch (ElementClickInterceptedException exception) {
			log.info("Unable to Click on the Element " + exception.getStackTrace());
		}
	}

	public void enterText(String sLocator, String sTextInput) {
		try {
			findElement(sLocator).sendKeys(sTextInput);
		} catch (ElementClickInterceptedException exception) {
			log.info("Element not click interaceptable: " + exception.getStackTrace());
		}
	}

	public String getInnerText(String sLocator) {
		String innerText = null;

		try {
			innerText = findElement(sLocator).getText();
		} catch (Exception exception) {
			log.info("Unable to get inner text of given element: " + exception.getStackTrace());
		}
		return innerText;
	}

	public String getAttribute(String sLocator, String sAttributeName) {
		String attributeValue = null;

		try {
			attributeValue = findElement(sLocator).getAttribute(sAttributeName);
		} catch (Exception exception) {
			log.info("Unable to get attribute: " + sAttributeName + " of given element: " + exception.getStackTrace());
		}
		return attributeValue;
	}

	public void navigateTo(String url) {
		driver.get(url);
	}

	public void selectFromDropdown(String sLocator, String sSelectBy, String sValue) {
		try {
			Select select = new Select(findElement(sLocator));

			switch (sSelectBy) {
			case "Index":
				select.selectByIndex(Integer.parseInt(sValue));
				break;

			case "Value":
				select.selectByValue(sValue);
				break;

			case "VisibleText":
				select.selectByVisibleText(sValue);
				break;

			default:
				break;
			}

		} catch (Exception exception) {
			log.info("Unable to select from dropdown " + exception.getStackTrace());
		}
	}

	public void scrollToElement(String sLocator) {
		try {
			Actions action = new Actions(driver);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", findElement(sLocator));
		} catch (Exception exception) {
			log.info("Unable to scroll to given element: " + exception.getStackTrace());
		}
	}

}
