package com.demosite.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demosite.pages.Baseclass;
import com.demosite.pages.LoginPage;
import com.demosite.utilities.BrowserFactory;
import com.demosite.utilities.ExcelDataProvider;
import com.demosite.utilities.Helper;

public class LoginTest extends Baseclass {
	
	
	@Test(dataProvider = "DataInput",dataProviderClass = ExcelDataProvider.class)
	public void loginToDemosite(String UserName,String Password) throws Exception
	{
		logger=report.createTest("Login to Demosite");
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		login.loginToDemosite(UserName, Password);
		Thread.sleep(5000);
		if(!Helper.isAlertPresent(driver))
		{
			String url=driver.getCurrentUrl();
			String actTitle=driver.getTitle();
		    if(url.contentEquals("http://www.demo.guru99.com/V4/manager/Managerhomepage.php"))
		    {
		    	if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage"))
		    	{
		    		System.out.println("Handling valid inputs--Passed");
		    		logger.pass("Handling valid inputs--Passed");
		    	}
		    }
		}
	    else 
	    {
	    	  String alertMessage=Helper.acceptAlert(driver);
	    	   if(alertMessage.equalsIgnoreCase("User or Password is not valid"))
	    	   {
	    		   System.out.println("Handling Invalid inputs--Passed");
	    		   logger.pass("Handling Invalid inputs--Passed");
	    	   }
	    }
		
	}
	
	

}
