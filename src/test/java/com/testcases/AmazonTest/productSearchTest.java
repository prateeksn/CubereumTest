package com.testcases.AmazonTest;

import java.util.Hashtable;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pom.loginLandingpage.LaunchPage;
import com.pom.loginLandingpage.LoginPage;
import com.pom.pages.AmazonPage;
import com.pom.utilities.ExcelReader;
import com.pom.utilities.Constants;
import com.relevantcodes.extentreports.LogStatus;
import com.testcases.BaseTest;

public class productSearchTest extends BaseTest {
	String testCaseName = "Product_Search_Test";

	@Test(dataProvider = "getData")
	public void doLogin(Hashtable<String, String> data) throws Exception {
		test = extent.startTest(testCaseName);
		if (!ExcelReader.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		test.log(LogStatus.INFO, "Starting login test");
		test.log(LogStatus.INFO, "Opening browser");
		init(data.get("Browser"));

		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);
		launchPage.gotoLoginPage();

		test.log(LogStatus.INFO," *** About to start the Execution of Testcase 1 ***");
		AmazonPage amazon = new AmazonPage(driver, test);
		PageFactory.initElements(driver, amazon);
		amazon.search(data.get("searchText"));	
		test.log(LogStatus.INFO," Adding filters to the results ");
		amazon.addFilters(data.get("display"),data.get("material"));
		test.log(LogStatus.INFO," *** Execution is completed for Testcase 1 ***");
		 
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
