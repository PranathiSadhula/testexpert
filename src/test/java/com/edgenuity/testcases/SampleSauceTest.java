package com.edgenuity.testcases;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class SampleSauceTest {
	 
	  public static final String USERNAME = "EdgeQA";
	  public static final String ACCESS_KEY = "aa13e094-1810-468b-a678-c19f7424d59d";
	  public static final String URL = "https://EdgeQA:aa13e094-1810-468b-a678-c19f7424d59d@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	 
	  public static void main(String[] args) throws Exception {
	 
		  
	    DesiredCapabilities caps =new  DesiredCapabilities();
	    caps.setCapability("platform", "WIN10");
	    caps.setCapability("version", "latest");
	    caps.setCapability("browserName", "Chrome");
	 
	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);

	    driver.get("https://auth.qa.edgenuity.com/Login/Login/");
	    System.out.println("title of page is: " + driver.getTitle());
	 
	    driver.quit();
	  }
	}
