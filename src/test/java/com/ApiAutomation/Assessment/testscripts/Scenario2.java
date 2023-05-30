package com.ApiAutomation.Assessment.testscripts;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ApiAutomation.Assessment.utils.RequestcallMethods;

public class Scenario2 {
	public RequestcallMethods method;

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
	public void getbyID() {
		method = new RequestcallMethods();
		method.getbyID();
	}

	@Test(priority = 3)
	public void getbystatus() {
		method = new RequestcallMethods();
		method.getbyStatus();
	}

	@Test(priority = 4)
	public void deletepet() {
		method = new RequestcallMethods();
		method.deletepet();
	}

}
