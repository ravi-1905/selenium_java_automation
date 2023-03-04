package com.demo.validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class Validations {
	
	private Logger log = LogManager.getLogger(Validations.class.getName());

	public void verifyText(String sActualValue, String sExpectedValue) {
		try {
			Assert.assertEquals(sActualValue, sExpectedValue);
			log.info("Actual value: "+sActualValue +" matched with expected value: "+sExpectedValue);
			log.info(Status.PASS);
		} catch (Exception e) {
			log.info("Actual value: "+sActualValue +" does not match with expected value: "+sExpectedValue);
			log.info(Status.FAIL);
		}
	}
}
