package com.ApiAutomation.Assessment.testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ApiAutomation.Assessment.utils.CommonUtils;
import com.ApiAutomation.Assessment.utils.RequestcallMethods;
import com.ApiAutomation.Assessment.utils.ValidationResults;

public class Scenario1 {
	public RequestcallMethods method;
	public ValidationResults validate;
	@BeforeSuite
	public void beforeSuite() {
		method = new RequestcallMethods();
		method.beforeSuite();
	}

	@Test(priority = 1)
	public void postpet() {
		method = new RequestcallMethods();
		method.postpet();
	}

	@Test(priority = 2)
	public void postpetimage() {
		method = new RequestcallMethods();
		method.postimage();
	}

	@Test(priority = 3)
	public void getbyID() {
		method = new RequestcallMethods();
		method.getbyID();
	}

	@Test(priority = 4)
	public void getbystatus() {
		method = new RequestcallMethods();
		method.getbyStatus();
	}
	
	@AfterMethod
	public void validations() {
		validate = new ValidationResults();
		validate.petpostvalidations();
	}

}
