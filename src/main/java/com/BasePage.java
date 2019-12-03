package com;

import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.pom.utilities.ReadFromExcelSheet;
import com.pom.utilities.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {

	public WebDriver driver;

	public ExtentTest test;

	public BasePage() {
	}

	public BasePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	public void reportFailure(String failureMessage) 
	{
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		//Assert.fail(failureMessage);
	}
	
	 // ********** Function to Fail the test case and take the screenshot of the current active page and stops the execution **********
		public void reportFailureLog(String failureMessage) 
		{
			test.log(LogStatus.FAIL, failureMessage);
			takeScreenShot();
			AssertJUnit.fail(failureMessage);
		}
	// ********** Wait Until the Webelement is Enabled for given time  **********
			public static void waitUntilWebElementEnabled(WebDriver driver, final WebElement element, int timeoutInSeconds)
			{
			try {
					WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
					wait.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							return element.isEnabled();
						}
					});
			 	} 
			catch (Exception e)
				{
					// continue
				}
			}

	//********** Validate the Element present in the page **********
	public boolean isElementPresent(String locator)
	{
		//test.log(LogStatus.INFO, "Trying to find element -> " + locator);
		int s = driver.findElements(By.xpath(locator)).size();
		if (s == 0) {
			test.log(LogStatus.INFO, locator+"Element not found");
			return false;
		} else {
			test.log(LogStatus.INFO, locator+"Element found");
			return true;
		}
	}



	// ********** Function to take screenshot of the current page **********
	public void takeScreenShot()
	{
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		// String screenshotFolder = d.toString().replace(":", "_").replace(" ",
		// "_");
		String filePath = Constants.REPORTS_PATH + "screenshots//" + screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(filePath));
	}
	

	//  ********** Function to scroll into the webelement  **********
	public void scrollIntoElement(WebElement s1) throws InterruptedException
    {
       
        Actions actions= new Actions(driver);
        actions.moveToElement(s1);
        actions.perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(3000);
       
    }

}