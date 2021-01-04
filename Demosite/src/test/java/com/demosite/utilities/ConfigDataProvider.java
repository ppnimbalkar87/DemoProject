package com.demosite.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;
	public ConfigDataProvider()
	{
		File src=new File("./Config/Config.properties");
		try {
			FileInputStream fin=new FileInputStream(src);
			pro=new Properties();
			pro.load(fin);
		} catch (Exception e) {
			System.out.println("Unable to load Property file "+e.getMessage());
		} 
	}
	
	public String getDataFromConfig(String key)
	{
		return pro.getProperty(key);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("browser");
	}
	
	public String getAppUrl()
	{
		return pro.getProperty("url");
	}
}
