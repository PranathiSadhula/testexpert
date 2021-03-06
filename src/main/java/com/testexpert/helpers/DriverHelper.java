package com.testexpert.helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("rawtypes")
public class DriverHelper {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
	private static final ThreadLocal<IOSDriver> iosDriverHolder = new ThreadLocal<>();
	private static final ThreadLocal<AndroidDriver> androidDriverHolder = new ThreadLocal<>();
	private static final ThreadLocal<String> driverName = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver driver) {
		DriverHelper.driver.set(driver);
	}

	public static AppiumDriver getAppiumDriver() {
		return appiumDriver.get();
	}

	public static void setAppiumDriver(final AppiumDriver appiumDriver) {
		DriverHelper.appiumDriver.set(appiumDriver);
	}

	public static IOSDriver getIOSDriver() {
		return iosDriverHolder.get();
	}

	public static void setIOSDriver(final IOSDriver iosDriver) {
		iosDriverHolder.set(iosDriver);
	}

	public static AndroidDriver getAndroidDriver() {
		return androidDriverHolder.get();
	}

	public static void setAndroidDriver(final AndroidDriver androidDriver) {
		DriverHelper.androidDriverHolder.set(androidDriver);
	}

	public static String getDriverName() {
		return driverName.get();
	}

	public static void setDriverName(String driverName) {
		DriverHelper.driverName.set(driverName);
	}
}