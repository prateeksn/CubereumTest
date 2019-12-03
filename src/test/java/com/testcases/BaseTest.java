package com.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pom.utilities.ExtentReportManager;
import com.pom.utilities.Constants;
import com.pom.utilities.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	public ExtentReports extent = ExtentReportManager.getInstance();
	public ExtentTest test;
	public Xls_Reader xls = new Xls_Reader(Constants.DATA_XLS_PATH);
	public WebDriver driver;
	
// Initializing the browser and Launch of the browser
	public void init(String bType)
	{
		try {
		test.log(LogStatus.INFO, "Opening browser - " + bType);
		if (!Constants.GRID_RUN) {
			// local machine
			if (bType.equalsIgnoreCase("Mozilla"))
				driver = new FirefoxDriver();
			
			else if (bType.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_EXE);
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driver = new InternetExplorerDriver(ieCapabilities);
			}
			else if (bType.equalsIgnoreCase("Chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--disable-extensions");
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
				driver = new ChromeDriver(chromeOptions);
				
				/*System.setProperty("webdriver.chrome.driver", SFConstants.CHROME_DRIVER_EXE);
				ChromeOptions Options= new ChromeOptions();
				//ChromeOptions Options = new ChromeOptions();
				Options.addArguments("window-size=800,600");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY,Options);
				driver = new ChromeDriver(capabilities); */
			}
			else if(bType.equalsIgnoreCase("Edge"))
			{
				System.setProperty("webdriver.edge.driver", Constants.IE_DRIVER_EXE);
			    DesiredCapabilities capabilities = DesiredCapabilities.edge();
			    driver = new EdgeDriver(capabilities);
			}
		}
		
		else {
			// grid
			DesiredCapabilities cap = null;
			if (bType.equals("Mozilla")) {
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			} else if (bType.equals("Chrome")) {
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			try {
				// driver = new RemoteWebDriver(new
				// URL("http://localhost:4444/wd/hub"), cap);
				driver = new FirefoxDriver();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
		test.log(LogStatus.INFO, "Opened browser successfully - " + bType);
		} 
		catch (Exception e)
		{
			reportFailure("Error occured while intializing the browser, please check the browser drivers");
		}
	}

	public void reportFailure(String failureMessage) {
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}

	public void takeScreenShot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
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
		
	
}