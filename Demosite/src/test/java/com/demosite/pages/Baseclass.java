package com.demosite.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.demosite.utilities.BrowserFactory;
import com.demosite.utilities.ConfigDataProvider;
import com.demosite.utilities.ExcelDataProvider;
import com.demosite.utilities.Helper;

public class Baseclass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	@BeforeSuite
	public void setUpSuite() 
	{
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Demosite_"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
	}
	@BeforeClass
	public void setUp()
	{
		driver=BrowserFactory.startApplication(driver, config.getBrowser(),config.getAppUrl());
	}
	
	@AfterClass
	public void teardown()
	{
		BrowserFactory.quiteBrowser(driver);
	}

	@AfterMethod
	public void teardownMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			try {
				logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (Exception e) {
				System.out.println("Unable to attach Screenshot to Report");
			}
		}
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			try {
				logger.pass("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (Exception e) {
				System.out.println("Unable to attach Screenshot to Report");
			}
		}
		report.flush();
	}
	
	
}
