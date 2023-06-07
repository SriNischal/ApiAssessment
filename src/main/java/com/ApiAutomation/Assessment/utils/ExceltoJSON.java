package com.ApiAutomation.Assessment.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceltoJSON {

    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\exceltojson.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet

            String jsonData = convertSheetToJson(sheet);

            workbook.close();

            System.out.println(jsonData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertSheetToJson(Sheet sheet) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\n");

        int rows = sheet.getPhysicalNumberOfRows();
        Row headerRow = sheet.getRow(0);
        int columns = headerRow.getLastCellNum();

        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            jsonBuilder.append("\t{\n");

            for (int j = 0; j < columns; j++) {
                Cell headerCell = headerRow.getCell(j);
                Cell cell = row.getCell(j);

                if (cell == null) {
                    jsonBuilder.append("\t\t\"").append(headerCell.getStringCellValue()).append("\": null,\n");
                } else {
                    switch (cell.getCellType()) {
                        case STRING:
                            jsonBuilder.append("\t\t\"").append(headerCell.getStringCellValue()).append("\": \"")
                                    .append(cell.getStringCellValue()).append("\",\n");
                            break;
                        case NUMERIC:
                            jsonBuilder.append("\t\t\"").append(headerCell.getStringCellValue()).append("\": ")
                                    .append(cell.getNumericCellValue()).append(",\n");
                            break;
                        case BOOLEAN:
                            jsonBuilder.append("\t\t\"").append(headerCell.getStringCellValue()).append("\": ")
                                    .append(cell.getBooleanCellValue()).append(",\n");
                            break;
                        default:
                            jsonBuilder.append("\t\t\"").append(headerCell.getStringCellValue()).append("\": null,\n");
                            break;
                    }
                }
            }

            // Remove the trailing comma from the last property
            jsonBuilder.delete(jsonBuilder.length() - 2, jsonBuilder.length() - 1);

            jsonBuilder.append("\t},\n");
        }

        // Remove the trailing comma from the last object
        jsonBuilder.delete(jsonBuilder.length() - 2, jsonBuilder.length() - 1);

        jsonBuilder.append("\n");

        return jsonBuilder.toString();
    }
}
