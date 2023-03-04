package com.demo.base;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.demo.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties prop;
	private Logger log = LogManager.getLogger(Base.class.getName());

	public WebDriver initializeDriver() throws IOException {

		prop = Utils.loadConfig(Constants.CONFIG_FILE_PATH);
		String browserName = prop.getProperty("browser");
		String appURL = prop.getProperty("app_url");
		int timeOut = Integer.parseInt(prop.getProperty("Implicit_timeout"));

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else {
			System.out.println(browserName + " is not a valid browser");
		}

		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		log.info("Driver is initialized.");
		
		driver.get(appURL);

		return driver;

	}

}
