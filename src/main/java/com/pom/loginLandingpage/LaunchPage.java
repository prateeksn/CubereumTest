package com.pom.loginLandingpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.BasePage;
import com.pom.utilities.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LaunchPage extends BasePage{
	
	//Web elements
	public static final String HOME_BUTTON = "//a[@id='dashboard' and text()='Home']";
	public static final String ALERT_OK_BUTTON = "//button[@id='AlertButton']";
	
	@FindBy(xpath=HOME_BUTTON)
	public WebElement home_button;
	
	@FindBy(xpath=ALERT_OK_BUTTON)
	public WebElement alert_ok_button;
	
	public LaunchPage(WebDriver driver,ExtentTest test)
	{
		super(driver,test);
	}
		
	public LoginPage gotoLoginPage() 
	{
		String BasePageTitle ="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		try{
			test.log(LogStatus.INFO, "Opening the url - "+Constants.TEST_HOMEPAGE_URL);	
			driver.get(Constants.TEST_HOMEPAGE_URL);
		}
		catch (Exception e)
		{
			reportFailure("Error occured while loading application");
			
		}
		String s1 =  driver.getTitle(); // capturing the opened URL title 
		if(s1.equals(BasePageTitle)) // Verifying the URL 
		{
			test.log(LogStatus.INFO, "URL Opened - "+Constants.TEST_HOMEPAGE_URL);
			test.log(LogStatus.PASS,"Base title is matching");
	
		}
		else
		{	
			reportFailure("Fail - Title not matching, plesae check the application URL");
		}	
		
		LoginPage loginPage = new LoginPage(driver,test);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
		}
}