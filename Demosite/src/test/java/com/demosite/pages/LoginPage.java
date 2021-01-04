package com.demosite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}

	//Find all Locators
		@FindBy(xpath="//input[@name='uid']") private WebElement username;
		@FindBy(xpath="//input[@name='password']") private WebElement password;
		@FindBy(xpath="//input[@name='btnLogin']") private WebElement LoginButton;
		@FindBy(xpath="//input[@name='btnReset']") WebElement ResetButton;
	
		public void loginToDemosite(String AppUsername,String AppPassword)
		{
			username.sendKeys(AppUsername);
			password.sendKeys(AppPassword);
			LoginButton.click();
		}
	
}
