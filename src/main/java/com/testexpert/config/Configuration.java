package com.testexpert.config;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * The Configuration is a final class intended to set default system properties
 * for below configurations used to execute tests: **platform** **browser**
 * **timeout** **runWithGrid** **gridHubUrl** **headless** 
 * **DesiredCapabilities customCaps**
 * 
 * @author Pranathi.sadhula
 */
public final class Configuration {

	/**
	 * Marking Configuration as private constructor so it is not intended to extend
	 * or instantiate an object. The purpose of this class is to set default system
	 * properties required to set test execution environment.
	 */
	private Configuration() {
	}

	/**
	 * platform is set default to windows. 
	 * System.getProperty("test.platform", "windows") returns "windows" if there is no property set at runtime. 
	 * Can be set to required test execution environment on runtime using examples:
	 * 1.System.setProperty("test.platform","windows")
	 * 2.System.setProperty("test.platform","android")
	 * 3.System.setProperty("test.platform","linux")
	 * 4.System.setProperty("test.platform","ios")
	 */
	public static String platform = System.getProperty("test.platform", "windows");

	/**
	 * browser is set default to chrome.
	 * System.getProperty("test.browser", "chrome") returns "chrome" if there is no property set at runtime. 
	 *  Can be set to required test execution environment on runtime using example:
	 * 1.System.setProperty("test.browser","chrome")
	 * 2.System.setProperty("test.browser","firefox")
	 * 3.System.setProperty("test.browser","edge")
	 * 4.System.setProperty("test.browser","safari")
	 */
	public static String browser = System.getProperty("test.browser", "chrome");

	/**
	 * runWithGrid is set default to false. 
	 * System.getProperty("test.runWithGrid", "false")  returns "false" if there is no property set at runtime.
	 * Can be set to required test execution
	 * environment on runtime using example:
	 * 1.System.setProperty("test.runWithGrid","true")
	 * Purpose of setting runWithGrid property to set test execution environment with grid or not.
	 */
	public static boolean runWithGrid = Boolean.parseBoolean(System.getProperty("test.runWithGrid", "false"));

	/**
	 * gridHubUrl is set default to http://localhost:4444/wd/hub. 
	 * Can be set to required test execution
	 * environment on runtime using example:
	 * System.getProperty("test.gridHubUrl", "http://127.0.0.1:4723/wd/hub")
	 */
	public static String gridHubUrl = System.getProperty("test.gridHubUrl", "http://localhost:4444/wd/hub");
	/**
	 * headless is set default to false. Can be set to required test execution
	 * environment on runtime using example:
	 * System.setProperty("test.headless", "true")
	 */
	public static boolean headless = Boolean.parseBoolean(System.getProperty("test.headless", "false"));
	/**
	 * customCaps can be set using static call : Configuration.customCaps
	 */
	public static DesiredCapabilities customCaps = new DesiredCapabilities();
}
