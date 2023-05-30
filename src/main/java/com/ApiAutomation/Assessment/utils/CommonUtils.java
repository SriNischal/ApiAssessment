package com.ApiAutomation.Assessment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class CommonUtils {
	private FileInputStream path;
	protected RequestSpecification request;
	public FileReader jsondata;
	private Properties prop;
	public JSONObject input;
	public Object jsonbody;
	public String helperutuil(String name) {
		prop = new Properties();
		try {
			path = new FileInputStream(
					ProjectbasedConstantPaths.TEST_DATA);
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		try {
			prop.load(path);
		} catch (IOException e) {
			System.out.println("path is invalid");
		}
		return prop.getProperty(name);
	}
	
	public RequestSpecification getjsondata(String path) {
		JSONParser data = new JSONParser();
		try {
			jsondata = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			jsonbody = data.parse(jsondata);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        input = (JSONObject) jsonbody;
        return RestAssured.given().body(input.toJSONString());
	}

}
