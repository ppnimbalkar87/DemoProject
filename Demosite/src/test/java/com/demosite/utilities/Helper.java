package com.demosite.utilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	
	/**
	 * method to captures the screenshot of web-page
	 * @param driver
	 * @return path to screenshot captured
	 */
	public static String captureScreenshot(WebDriver driver) 
	{
		String ScreenshotPath=System.getProperty("user.dir")+"/Screenshots/Demosite_"+ getCurrentDateTime()+".png";
//		if(isAlertPresent(driver))
//		{
			try {
				BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(image, "png", new File(ScreenshotPath));
			} catch (Exception e) {
				System.out.println("Unable to capture Screenshot "+e.getMessage());
			} 
//		}
//		else
//		{
//			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			try 
//			{
//				FileHandler.copy(src, new File(ScreenshotPath));
//			} catch (Exception e)
//			{
//				System.out.println("Unable to capture Screenshot "+e.getMessage());
//			}
//		}
		return ScreenshotPath;
	}
	
	/**
	 * Method gives current date-time in MM_dd_yy_HH_mm_ss formate
	 * @return nothing
	 */
	public static String getCurrentDateTime()
	{
		DateFormat customFormate=new SimpleDateFormat("MM_dd_yy_HH_mm_ss");
		Date currentdate=new Date();
		return customFormate.format(currentdate);
	}
	
	
	/**
	 * Method to check alert is present on web-page
	 * @param driver
	 * @return true if alert present else false
	 */
	public static boolean isAlertPresent(WebDriver driver)
	{
	      try{
	          driver.switchTo().alert();
	          return true;
	      }catch(NoAlertPresentException ex)
	      {
	          return false;
	      }
	}
	
	/**
	 * accept alert by clicking OK button	
	 * @param driver
	 */
	public static String acceptAlert(WebDriver driver)
	{
		if(isAlertPresent(driver))
		{
			Alert alert=driver.switchTo().alert();
			String alert_Message=alert.getText();
			alert.accept();
			return alert_Message;
		}
		return null;

	}
	
	/**
	 * Dismiss alert by clicking Cancle button
	 * @param driver
	 */
	public static String dismissAlert(WebDriver driver)
	{
		if(isAlertPresent(driver))
		{
			Alert alert=driver.switchTo().alert();
			String alert_Message=alert.getText();
			alert.dismiss();
			return alert_Message;
		}
		return null;
	}
	
	
	/**
	 * Execute javascript-code against web-element
	 * @param driver
	 * @param script
	 * @param ele
	 */
	public static void runJavaScriptOnElement(WebDriver driver,String script,WebElement ele)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript(script, ele);
	}
}
