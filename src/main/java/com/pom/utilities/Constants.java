package com.pom.utilities;

public class Constants {
	public static final boolean GRID_RUN = false;

	// paths for browser's webDriver
	public static final String CHROME_DRIVER_EXE = System.getProperty("user.dir") + "\\src\\test\\resources\\webdrivers\\chromedriver.exe";
	public static final String IE_DRIVER_EXE = System.getProperty("user.dir") + "\\src\\test\\resources\\webdrivers\\IEDriverServer.exe";
	public static final String EDGE_DRIVER_EXE= System.getProperty("user.dir") + "\\src\\test\\resources\\webdrivers\\MicrosoftWebDriver.exe" ;
	
	// URLs-test
	public static final String TEST_HOMEPAGE_URL ="https://www.amazon.in/";

	// paths
	public static final String REPORTS_PATH = System.getProperty("user.dir") + "\\ExtentReports\\";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir") + "\\data\\SF_TestData.xlsx";
	

	//For the excel sheets and column
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
	
	public static final String IMPORT_FRESH_SHEET = "Original";


}