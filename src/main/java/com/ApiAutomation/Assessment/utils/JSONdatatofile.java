package com.ApiAutomation.Assessment.utils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
public class JSONdatatofile {
    public static void main(String[] args) throws Exception {
        //JSONArray jsonData = ConvertexceltoJSON.jsondata();
        String filePath = "D:\\AutomationAPI\\Assessment\\src\\main\\resources\\poperties\\output.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
          //  fileWriter.write(jsonData.toString());
            System.out.println("JSON data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}