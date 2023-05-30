package com.ApiAutomation.Assessment.utils;

import java.io.File;

public class ProjectbasedConstantPaths {
	public final static String USER_HOME = System.getProperty("user.dir") + File.separator;
    public final static String TEST_DATA = USER_HOME + File.separator + "src/main/resources"+ File.separator + "poperties"
            + File.separator + "testdata.properties";
    public final static String JSON_DATA = USER_HOME + File.separator + "src/main/resources"+ File.separator + "poperties"
            + File.separator + "testdata.json";
    public final static String PUT_JSON_DATA = USER_HOME + File.separator + "src/main/resources"+ File.separator + "poperties"
            + File.separator + "puttestdata.json";
}
