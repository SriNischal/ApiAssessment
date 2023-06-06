package com.ApiAutomation.Assessment.utils;

import java.io.File;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestcallMethods {
	public  CommonUtils helper;
	protected RequestSpecification request;
	protected Response response;
	
	
	public final static Logger LOGGER = LogManager.getLogger(RequestcallMethods.class);
	public void beforeSuite() {
		helper = new CommonUtils();
		LOGGER.info("Started executing before suite");
		RestAssured.baseURI= helper.helperutuil("BaseURL");
		LOGGER.info("Before Suite Executed Successfully");
	}
	public void postpet() {
		helper = new CommonUtils();
		request = helper.getjsondata(ProjectbasedConstantPaths.JSON_DATA);
		LOGGER.info("Started creating pet details for the new id");
		response = request.when().header("Content-Type", helper.helperutuil("ContentType"))
	            .post().then().log()
				.all().extract().response();
		LOGGER.info("Details created for the new id");
		
	}
	public void postimage() {
		helper = new CommonUtils();
		LOGGER.info("Started posting the pet image");
		response = RestAssured.given().header("Content-Type", "multipart/form-data")
	            .multiPart("file", new File(helper.helperutuil("FilePath")))
	            .multiPart("additionalMetadata" , helper.helperutuil("AdditionalMetadata")).post(helper.helperutuil("Imageupload")).then().log()
				.all().extract().response();
		LOGGER.info("Pet image uploaded successfully");
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("message", Matchers.equalTo(helper.helperutuil("imageuploadedto")));
	}
	public void getbyID() {
		helper = new CommonUtils();
		LOGGER.info("Started fetching the pet details using id");
		response = RestAssured.given().when().get(helper.helperutuil("getbasepath")).then().log()
				.all().extract().response();
		LOGGER.info("Fetched the pet details using the id");
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("id", Matchers.equalTo(Integer.parseInt(helper.helperutuil("id"))));
		response.then().assertThat().body("category.name", Matchers.equalTo(helper.helperutuil("categoryname")));
		response.then().assertThat().body("tags", hasItem(hasEntry("name", helper.helperutuil("tagname"))));
	}
	public void getbyStatus() {
		helper = new CommonUtils();
		LOGGER.info("Started fetching the pet details using status");
		response = RestAssured.given().when().get(helper.helperutuil("getpetbystatus")).then().log()
				.all().extract().response();
		LOGGER.info("Fetched the pet details using the status");
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("id", hasItem(Matchers.equalTo(Integer.parseInt(helper.helperutuil("id")))));
		response.then().assertThat().body("category.name", hasItem(Matchers.equalTo(helper.helperutuil("categoryname"))));
		response.then().assertThat().body("status", hasItem(Matchers.equalTo(helper.helperutuil("status"))));
	}
	public void deletepet() {
		helper = new CommonUtils();
		response = RestAssured.given().when().delete(helper.helperutuil("getbasepath")).then().log()
				.all().extract().response();
		System.out.println(response);
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("message", Matchers.equalTo(helper.helperutuil("id")));
	}
	
	public void updatepet() {
		helper = new CommonUtils();
		LOGGER.info("Started updating pet details for the created id");
		request = helper.getjsondata(ProjectbasedConstantPaths.PUT_JSON_DATA);
		response = request.header("Content-Type", helper.helperutuil("ContentType")).when().put().then().log().all().extract().response();
		LOGGER.info("Details updated for the created id");
		assertThat(response.getStatusCode(), is(Integer.parseInt(helper.helperutuil("code"))));
		assertThat(response.getStatusLine(), equalTo(helper.helperutuil("statusline")));
		assertThat(response.body(), notNullValue());
		response.then().assertThat().body("id", Matchers.equalTo(Integer.parseInt(helper.helperutuil("id"))));
		response.then().assertThat().body("category.name", Matchers.equalTo(helper.helperutuil("putcategoryname")));
		response.then().assertThat().body("tags", hasItem(hasEntry("name", helper.helperutuil("puttagname"))));
	}

}
