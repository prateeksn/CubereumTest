package com.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pom.loginLandingpage.LaunchPage;
import com.pom.loginLandingpage.LoginPage;
import com.pom.utilities.ExcelReader;
import com.pom.utilities.Constants;
import com.relevantcodes.extentreports.LogStatus;
//testing checkin..
public class LoginTest extends BaseTest {
	
	
	String testCaseName = "LoginTest";
	@Test(dataProvider = "getData")
	public void doLogin(Hashtable<String, String> data) {
		test = extent.startTest("Login Test");
		if (!ExcelReader.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")) 
		{
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		test.log(LogStatus.INFO, "Starting login test");
		test.log(LogStatus.INFO, "Opening browser");
		init(data.get("Browser"));

		
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);
		launchPage.gotoLoginPage();
		test.log(LogStatus.INFO, "Basetittle is compared");	
		
		test.log(LogStatus.INFO, "Logging in");
		LoginPage lp = new LoginPage(driver, test);
		PageFactory.initElements(driver, lp);
		lp.sf_Login(data.get("UserName"), data.get("Password"));
		
		//Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));
		/*
		
		String actualResult = "";
		// if User logged in
		if (page instanceof LandingPage)
			actualResult = "Success";
		else
			actualResult = "Unsuccessful";
		if (!actualResult.equals(data.get("ExpectedResult"))) {
			// reportFailure("failure message");
			loginPage.takeScreenShot();
			test.log(LogStatus.FAIL, "Login Test Failed");
		} else {
			loginPage.takeScreenShot();
			test.log(LogStatus.PASS, "Login Test Passed");
		}*/
	}

	@AfterMethod
	public void quit() {
		if (extent != null) {
			extent.endTest(test);
			extent.flush();
		}
		if (driver != null)
			driver.quit();
	}

	@DataProvider
	public Object[][] getData() {
		return ExcelReader.getData(xls, testCaseName);
	}
}