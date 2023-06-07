package com.ApiAutomation.Assessment.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Multiplearrays {

	public static void main(String[] args) {
		try {
			FileInputStream file = new FileInputStream(
					"D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\exceltojson.xlsx");
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet

			String jsonData;
			jsonData = convertSheetToJson(sheet);

			workbook.close();

			System.out.println(jsonData);
			String filePath = "D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\output.json";
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

	public static String convertSheetToJson(Sheet sheet) {
		Row row = sheet.getRow(1);
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
