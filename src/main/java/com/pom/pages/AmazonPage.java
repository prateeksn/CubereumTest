package com.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AmazonPage extends BasePage{
	
	public static final String SEARCHBOX = "//input[@id='twotabsearchtextbox']";
	public static final String SEARCH_BTN="//span[@id='nav-search-submit-text']";
	public static final String ANALOGUE="//span[text()='Analogue']";
	public static final String MATERIAL="//span[text()='Leather']";
	public static final String RESULT="//span[text()='1-48 of over 50,000 results for']";
	
	@FindBy(xpath=SEARCHBOX)
	public WebElement searchbox;
	
	@FindBy(xpath=SEARCH_BTN)
	public WebElement search_btn;
	
	@FindBy(xpath=ANALOGUE)
	public WebElement analogue;
	
	@FindBy(xpath=MATERIAL)
	public WebElement material;
	
	@FindBy(xpath=RESULT)
	public WebElement result;
	
	public AmazonPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}
	
	public void search(String text) throws InterruptedException {
		try {
		searchbox.click();
		searchbox.sendKeys(text);
		searchbox.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		waitUntilWebElementEnabled(driver, result, 5000);
		test.log(LogStatus.INFO, "Text entered in Search field");
		if(isElementPresent(RESULT)){
			test.log(LogStatus.PASS, "Seach results displayed ");
		}
		else {
			test.log(LogStatus.FAIL, "Seach results not displayed ");
		}}
		catch (Exception e) {
			reportFailure("Error occured while searching for a product");
		}
	}
	
	public void addFilters(String display, String materials) 
	{
		try {
		scrollIntoElement(analogue);
		driver.findElement(By.xpath("//span[text()='" + display + "']")).click();
		test.log(LogStatus.INFO, display+" --> Added as a filter in display section ");
		waitUntilWebElementEnabled(driver, analogue, 5000);
		Thread.sleep(4000);
		scrollIntoElement(material);
		driver.findElement(By.xpath("//span[text()='" + materials + "']")).click();
		waitUntilWebElementEnabled(driver, material, 5000);
		Thread.sleep(4000);
		test.log(LogStatus.INFO, materials+" --> Added as a filter in materials section ");
		} catch (Exception e) {
			reportFailure("Error occured while appliying filters");
		}
		
	}
}
