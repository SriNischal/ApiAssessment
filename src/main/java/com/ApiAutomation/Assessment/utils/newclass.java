package com.ApiAutomation.Assessment.utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class newclass {
	public static Workbook workbook;

	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream(
					"D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\exceltojson.xlsx");
			workbook = new XSSFWorkbook(file);

			String jsonData;
			jsonData = convertSheetToJson("put");

			workbook.close();

			System.out.println(jsonData);
			String filePath = "D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\putoutputlatest.json";
			try (FileWriter fileWriter = new FileWriter(filePath)) {
				fileWriter.write(jsonData.toString());
				System.out.println("JSON data has been written to the file.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String convertSheetToJson(String sheetname) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		Sheet sheet = null;
		if (sheetname.equals("post")) {
			sheet = workbook.getSheet(sheetname);
		} else if (sheetname.equals("put")) {
			sheet = workbook.getSheet(sheetname);
		}
		if (sheet != null) { 
			for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);
				int j = 0;
				while (j >= 0) {
					int id = (int) row.getCell(j).getNumericCellValue();
					j++;
					int categoryId = (int) row.getCell(j).getNumericCellValue();
					j++;
					String categoryName = row.getCell(j).getStringCellValue();
					j++;
					String name = row.getCell(j).getStringCellValue();
					j++;
					String photoUrl = row.getCell(j).getStringCellValue();
					j++;
					int tagId = (int) row.getCell(j).getNumericCellValue();
					j++;
					String tagName = row.getCell(j).getStringCellValue();
					j++;
					String status = row.getCell(j).getStringCellValue();
					jsonObject.put("id", id);

					JSONObject categoryObject = new JSONObject();
					categoryObject.put("id", categoryId);
					categoryObject.put("name", categoryName);
					jsonObject.put("category", categoryObject);

					jsonObject.put("name", name);

					JSONArray photoUrlsArray = new JSONArray();
					photoUrlsArray.put(photoUrl);
					jsonObject.put("photoUrls", photoUrlsArray);

					JSONArray tagsArray = new JSONArray();
					JSONObject tagObject = new JSONObject();
					tagObject.put("id", tagId);
					tagObject.put("name", tagName);
					tagsArray.put(tagObject);
					jsonObject.put("tags", tagsArray);

					jsonObject.put("status", status);

					jsonArray.put(jsonObject);
					String jsonString = jsonObject.toString();
					StringBuilder appendedJson = new StringBuilder();
					appendedJson.append(jsonString);
					return jsonString;
				}
			}
		}
		return sheetname;
	}
}
