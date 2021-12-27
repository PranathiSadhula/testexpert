package com.testexpert.driver.factory;

import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
	
	    public static void initDriver(DesiredCapabilities extraCaps) {
	       new TestExpertDriver().init(extraCaps);
	       
	    }
	    public static void initDriver(){
	        initDriver(new DesiredCapabilities());
	    }

	   
}
