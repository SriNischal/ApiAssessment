package com.ApiAutomation.Assessment.utils;

import java.io.FileWriter;
import java.io.IOException;

public class JSONdatatofile {

	public static void main(String[] args) throws Exception {
		ConvertexceltoJSON json = new ConvertexceltoJSON();
		String jsonData = json.jsondata(); // Replace with your JSON data

		String filePath = "D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\output.json"; // Replace with the desired file path

		try (FileWriter fileWriter = new FileWriter(filePath)) {
			fileWriter.write(jsonData);
			System.out.println("JSON data has been written to the file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
