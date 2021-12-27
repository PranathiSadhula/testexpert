package com.edgenuity.testexpert.automation.application.base;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;

import com.edgenuity.testexpert.automation.ui.pages.ApplicationBasePage;
import com.testexpert.helpers.DriverHelper;
import com.testexpert.utils.basetests.WebDriverBase;

import io.appium.java_client.remote.MobileCapabilityType;

@Listeners(com.testexpert.helpers.AllureReportListener.class)
public class ApplicationWebDriverBase extends WebDriverBase {

	 static final String hubURL = "https://EdgeQA:aa13e094-1810-468b-a678-c19f7424d59d@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	 
	public void openApplication(String url) {
		get(ApplicationBasePage.class, url);
		DriverHelper.getDriver().manage().window().maximize();
	}

	public void openApplicationInMobileBrowser(String url) {
		System.setProperty("test.platform", "android");
		System.setProperty("test.browser", "chrome");
		System.setProperty("test.runWithGrid", "true");
		System.setProperty("test.gridHubUrl", "http://127.0.0.1:4723/wd/hub");

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "HTC Desire 828");
		caps.setCapability(MobileCapabilityType.UDID, "192.168.1.225:5555");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		get(ApplicationBasePage.class, url, caps);

	}
	
	public void openAppInSauceLabs(String url, DesiredCapabilities caps ) {
		System.setProperty("test.runWithGrid", "true");
		System.setProperty("test.gridHubUrl", hubURL);
		get(ApplicationBasePage.class, url, caps);
	}
	

	public void closeApplication() {
		DriverHelper.getDriver().close();
	}

	public void quitApplication() {
		DriverHelper.getDriver().quit();
	}

}
