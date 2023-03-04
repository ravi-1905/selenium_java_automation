package com.demo.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.reports.ExtentReporterNG;
import com.demo.utils.Utils;



public class Listeners extends Utils implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	String testMethodName;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		testMethodName = result.getMethod().getMethodName();
		test = extent.createTest(testMethodName);
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, testMethodName+" has been Passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		try {
			extentTest.get().log(Status.FAIL, testMethodName+" has been Failed");
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName, driver), testMethodName);
		} catch (Exception e) {
			System.out.println("Unable to take Screenshot.");
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, testMethodName+" has been Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
