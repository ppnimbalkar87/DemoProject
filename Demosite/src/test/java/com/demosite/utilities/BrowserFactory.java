package com.demosite.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory
{
	public static WebDriver startApplication(WebDriver driver,String BrowserName,String AppUrl)
	{
		if(BrowserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");		
			driver=new ChromeDriver();
		}
		else if(BrowserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");	
		}
		else
		{
			System.out.println("We do not support this browser");
		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(AppUrl);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return driver;
	}
	
	public static void quiteBrowser(WebDriver driver)
	{
		driver.quit();
	}
}
