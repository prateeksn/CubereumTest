package com.pom.loginLandingpage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.BasePage;
import com.pom.utilities.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BasePage{
	
	/*@FindBy(xpath=SFConstants.LOGIN)
	public WebElement login;
	
	@FindBy(xpath=SFConstants.LOGIN_USERNAME)
	public WebElement username;
	
	@FindBy(xpath=SFConstants.LOGIN_PASSWORD)
	public WebElement password;
*/	
	//Login page objects 
		
		public static final String USER_NAME="//input[@name= 'username']";
		public static final String PASSWORD="//input[@name='pw']";
		public static final String LOGIN_BTN="//input[@name= 'Login' and @type ='submit']";
		public static final String QA_VERIFY="//span[contains(.,' Sandbox: QA')]";
		public static final String TWO_STEP_VERIFICATION="//h2[@id='header']";
		public static final String USERNAME_LABEL="//label[@for='username']";
		public static final String SESSION_ENDED_MSG="//*[text()='Your session has ended']";
		public static final String LOGIN="//button[text()='Log In']";
		
	
	@FindBy(xpath=LOGIN)
	public WebElement login;
		
	@FindBy(xpath=USER_NAME)
	public WebElement user_name;
	
	@FindBy(xpath=SESSION_ENDED_MSG)
	public WebElement session_ended_msg;
	
	@FindBy(xpath=PASSWORD)
	public WebElement password;
	
	@FindBy(xpath=LOGIN_BTN)
	public WebElement login_btn;
	
	@FindBy(xpath=LOGIN_BTN)
	public WebElement qa_verify;

	@FindBy(xpath=TWO_STEP_VERIFICATION)
	public WebElement two_step_verification;
	
	@FindBy(xpath=USERNAME_LABEL)
	public WebElement username_label;
	

	public LoginPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	public void sf_Login(String User_Name, String Password)
	{
		try
		{
				driver.navigate().refresh();
				Thread.sleep(6000);
				waitUntilWebElementEnabled(driver, user_name, 10000);
				user_name.sendKeys(User_Name);
				password.sendKeys(Password);
				login_btn.click();
				Thread.sleep(3000);
				
				if(isElementPresent(QA_VERIFY))
				{
					 test.log(LogStatus.PASS,"Successfully Logged in with the user " +User_Name);
				}
				else if(isElementPresent(SESSION_ENDED_MSG)) {
					login.click();
					Thread.sleep(3000);
					driver.navigate().refresh();
					waitUntilWebElementEnabled(driver, qa_verify, 7000);	
				}
				else if (isElementPresent(TWO_STEP_VERIFICATION))
				{
					reportFailureLog("Two step verification is required, plesae mask the IP to avoid the verification");
				}
				else 
				{
					reportFailure("Login Failed - Please check the credentails");
	
				}			
		}
			
		catch (Exception e)		
		{
			 test.log(LogStatus.FAIL,"Unable to Login");
			 takeScreenShot();
			
		}
			
		
	}
	
/*	public void Breqs_login() throws InterruptedException
	{
		try{
			
		
		driver.get("http://ustr-erl2-0634.na.uis.unisys.com:9202/");
		Thread.sleep(4000);
		login_btn.click();
		//BREQS USER NAME AND PASSOWRD, can be updated accordingly once we get the Role Matrix
		breqs_user_name.sendKeys("10100");
		Thread.sleep(1000);
		breqs_password.sendKeys("$8$10100$");
		login_submit_btn.click();
		Thread.sleep(1000);
		}
		catch (Exception e)		
		{
			 test.log(LogStatus.FAIL,"There are some issues navigating to Login page");
			 takeScreenShot();
			
		}
		
		boolean loginSuccess = isElementPresent(WELCOME_USER);
		if(loginSuccess)
		{
			test.log(LogStatus.PASS, "Logged in successfully Page Opened");	
		}else{
			test.log(LogStatus.FAIL, "Login was unsuccessful");
		}	
	}*/
		
	
		
	/*public Object doLogin(String userName,String passWord)
	{
		
		String LoginTitle = "Collecting Officer Dashboard - My ASP.NET Application";
		
		//login.click();
		test.log(LogStatus.INFO, "Logging in as -"+userName+"/"+passWord);
		
		if(driver.getTitle().equals(LoginTitle)){
			test.log(LogStatus.PASS, "Login Page Opened");	
		}else{
			test.log(LogStatus.FAIL, "Login page not displayed");
		}
		*/
	//	username.sendKeys(userName);
	//	password.sendKeys(passWord);
	//	password.sendKeys(Keys.ENTER);
		// logic - validate
		//boolean loginSuccess=isElementPresent(SFConstants.LOGGED_USER);
		
		/*if(loginSuccess){
			test.log(LogStatus.INFO, "Login Success");
			LandingPage landingPage = new LandingPage(driver,test);
			PageFactory.initElements(driver, landingPage);
			return landingPage;
		}
		else{
			test.log(LogStatus.INFO, "Login Not Success");
			LoginPage loginPage = new LoginPage(driver,test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;*/
}	
	
