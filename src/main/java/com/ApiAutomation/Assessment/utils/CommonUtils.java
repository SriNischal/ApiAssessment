package com.ApiAutomation.Assessment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
			path = new FileInputStream(ProjectbasedConstantPaths.TEST_DATA);
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

	public String converttoJSON(Sheet sheet, int i) {
		Row row = sheet.getRow(i);
		int id = (int) row.getCell(0).getNumericCellValue();

		int categoryId = (int) row.getCell(0).getNumericCellValue();
		String categoryName = row.getCell(2).getStringCellValue();

		String name = row.getCell(3).getStringCellValue();

		String photoUrl = row.getCell(4).getStringCellValue();

		int tagId = (int) row.getCell(0).getNumericCellValue();
		String tagName = row.getCell(6).getStringCellValue();

		String status = row.getCell(7).getStringCellValue();

		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{\n");
		jsonBuilder.append("\t\"id\": ").append(id).append(",\n");
		jsonBuilder.append("\t\"category\": {\n");
		jsonBuilder.append("\t\t\"id\": ").append(categoryId).append(",\n");
		jsonBuilder.append("\t\t\"name\": \"").append(categoryName).append("\"\n");
		jsonBuilder.append("\t},\n");
		jsonBuilder.append("\t\"name\": \"").append(name).append("\",\n");
		jsonBuilder.append("\t\"photoUrls\": [\n");
		jsonBuilder.append("\t\t\"").append(photoUrl).append("\"\n");
		jsonBuilder.append("\t],\n");
		jsonBuilder.append("\t\"tags\": [\n");
		jsonBuilder.append("\t\t{\n");
		jsonBuilder.append("\t\t\t\"id\": ").append(tagId).append(",\n");
		jsonBuilder.append("\t\t\t\"name\": \"").append(tagName).append("\"\n");
		jsonBuilder.append("\t\t}\n");
		jsonBuilder.append("\t],\n");
		jsonBuilder.append("\t\"status\": \"").append(status).append("\"\n");
		jsonBuilder.append("}");

		return jsonBuilder.toString();
	}

}
