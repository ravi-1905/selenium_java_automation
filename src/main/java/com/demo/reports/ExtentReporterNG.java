package com.demo.reports;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports extent;

	public static ExtentReports getReportObject() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String path = System.getProperty("user.dir")+"//reports//"+dateFormat.format(new Date())+"//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", System.getProperty("user.name"));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		try {
			extent.setSystemInfo("Machine", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			System.out.println("Unable to capture hostname.");
		}
		
		return extent;
	}
}
