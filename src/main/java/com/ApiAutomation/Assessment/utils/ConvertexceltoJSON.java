package com.ApiAutomation.Assessment.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
public class ConvertexceltoJSON {
    public static void main(String [] args) throws Exception {
        FileInputStream file = new FileInputStream(
                "D:\\AutomationAPI\\Assessment\\src\\main\\\\resources\\poperties\\exceltojson.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0); 
        workbook.close();
        JSONArray jsonArray = new JSONArray();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            JSONObject jsonObject = new JSONObject();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                DataFormatter values = new DataFormatter();
                String key = values.formatCellValue(sheet.getRow(0).getCell(j));
                String value = values.formatCellValue(cell);
                jsonObject.put(key, value);
            }
            jsonArray.put(jsonObject);
        System.out.println(jsonObject);
        String filePath = "D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\output.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }}
}
}