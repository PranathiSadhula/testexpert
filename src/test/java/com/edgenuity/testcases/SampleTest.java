package com.edgenuity.testcases;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.edgenuity.testexpert.automation.application.base.ApplicationWebDriverBase;
import com.testexpert.helpers.assertions.AssertionsHelper;

public class SampleTest extends ApplicationWebDriverBase {

	@BeforeTest
	public void launchEdGenApp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platform", "WIN10");
		caps.setCapability("version", "latest");
		caps.setCapability("browserName", "Chrome");
		openAppInSauceLabs("https://auth.qa.edgenuity.com/Login/Login/", caps);

	}

	@Test
	public void printProfiles() {
		AssertionsHelper.softAssert().fail("marking test to fail to see allure reports");

		System.out.println("yet to write code");
	}

	@AfterTest
	public void closeBrowser() {
		closeApplication();
	}

}
