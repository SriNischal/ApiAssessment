package com.ApiAutomation.Assessment.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.Matchers;

import io.restassured.response.Response;

public class ValidationResults {
	protected Response response;
	public CommonUtils helper;

	public void petpostvalidations() {
		helper = new CommonUtils();
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("id", Matchers.equalTo(Integer.parseInt(helper.helperutuil("id"))));
		response.then().assertThat().body("category.name", Matchers.equalTo(helper.helperutuil("categoryname")));
		response.then().assertThat().body("tags", hasItem(hasEntry("name", helper.helperutuil("tagname"))));
	}

}
