package com.testexpert.driver.factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.testexpert.driver.capabilities.AndroidChromeCaps;
import com.testexpert.driver.capabilities.AndroidNativeAppCaps;
import com.testexpert.driver.capabilities.ChromeCaps;
import com.testexpert.driver.capabilities.EdgeCaps;
import com.testexpert.driver.capabilities.FireFoxCaps;
import com.testexpert.driver.capabilities.GeckoCaps;
import com.testexpert.driver.capabilities.IECaps;
import com.testexpert.driver.capabilities.IosNativeAppCaps;
import com.testexpert.driver.capabilities.IosSafariCaps;
import com.testexpert.driver.capabilities.MacSafariCaps;
import com.testexpert.driver.capabilities.TestExpertCaps;
import com.testexpert.helpers.DriverHelper;
import com.testexpert.helpers.TestParamsHelper;

import java.net.URL;

/**
 * Factory to create Driver for usage with Selenium Grid
 */
@SuppressWarnings("rawtypes")
public class RemoteDriverFactory implements DriverFactory {

    private final String browserName;
    private final String platformName;
    private final DesiredCapabilities customCaps;
    private final UnexpectedAlertBehaviour alertBehaviour = UnexpectedAlertBehaviour.ACCEPT;
    private final boolean isHeadless;
    private final URL gridUrl;

    RemoteDriverFactory(String browserName, String platformName, DesiredCapabilities customCaps, boolean isHeadless, URL gridUrl) {
        this.browserName = browserName;
        this.platformName = platformName;
        this.customCaps = customCaps;
        this.isHeadless = isHeadless;
        this.gridUrl = gridUrl;
    }

    @Override
    public WebDriver get() {
        switch (platformName.toLowerCase().trim()) {
            case WINDOWS: {
                return windowsDriver();
            }
            case MAC: {
                return macDriver();
            }
            case LINUX: {
                return linuxDriver();
            }
            case ANDROID: {
                return androidDriver();
            }
            case IOS: {
                return iosDriver();
            }
            default: {
                return throwException(browserName, platformName);
            }
        }
    }

    private WebDriver macDriver() {
        TestParamsHelper.setPlatform(MAC);
        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return createRemoteDriver(new ChromeCaps(customCaps, this.alertBehaviour, this.isHeadless, Platform.MAC), CHROME);
            }
            case GECKO: {
                return createRemoteDriver(new GeckoCaps(customCaps, this.alertBehaviour, Platform.MAC), GECKO);
            }
            case SAFARI:{
                return createRemoteDriver(new MacSafariCaps(customCaps), SAFARI);
            }
            default: {
                return throwException(browserName, MAC);
            }
        }
    }

    private WebDriver iosDriver() {
        TestParamsHelper.setPlatform(IOS);
        switch (browserName.toLowerCase().trim()) {
            case SAFARI: {
                return createIosDriver(new IosSafariCaps(customCaps), SAFARI);
            }
            case NATIVE_APP: {
                return createIosDriver(new IosNativeAppCaps(customCaps), NATIVE_APP);
            }
            default: {
                return throwException(browserName, IOS);
            }
        }
    }

    private WebDriver linuxDriver() {
        TestParamsHelper.setPlatform(LINUX);
        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return createRemoteDriver(new ChromeCaps(customCaps, this.alertBehaviour, this.isHeadless, Platform.LINUX), CHROME);
            }
            case FIREFOX: {
                return createRemoteDriver(new FireFoxCaps(customCaps, this.alertBehaviour, Platform.LINUX), FIREFOX);
            }
            case GECKO: {
                return createRemoteDriver(new GeckoCaps(customCaps, this.alertBehaviour, Platform.LINUX), GECKO);
            }
            default: {
                return throwException(browserName, LINUX);
            }
        }
    }

    private WebDriver androidDriver() {
        TestParamsHelper.setPlatform(ANDROID);
        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return createAndroidDriver(new AndroidChromeCaps(customCaps), CHROME);
            }
            case NATIVE_APP: {
                return createAndroidDriver(new AndroidNativeAppCaps(customCaps), NATIVE_APP);
            }
            default: {
                return throwException(browserName, ANDROID);
            }
        }
    }

    private WebDriver windowsDriver() {
        TestParamsHelper.setPlatform(WINDOWS);

        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return createRemoteDriver(new ChromeCaps(customCaps, this.alertBehaviour, this.isHeadless, Platform.WINDOWS), CHROME);
            }
            case FIREFOX: {
                return createRemoteDriver(new FireFoxCaps(customCaps, this.alertBehaviour, Platform.WINDOWS), FIREFOX);
            }
            case GECKO: {
                return createRemoteDriver(new GeckoCaps(customCaps, this.alertBehaviour, Platform.WINDOWS), GECKO);
            }
            case EDGE: {
                return createRemoteDriver(new EdgeCaps(customCaps, this.alertBehaviour), EDGE);
            }
            case IE: {
                return createRemoteDriver(new IECaps(customCaps, "", this.alertBehaviour), IE);
            }
            case IE11: {
                return createRemoteDriver(new IECaps(customCaps, "11", this.alertBehaviour), IE11);
            }
            case IE10: {
                return createRemoteDriver(new IECaps(customCaps, "10", this.alertBehaviour), IE10);
            }
            default: {
                return throwException(browserName, WINDOWS);
            }
        }
    }

    private RemoteWebDriver createRemoteDriver(TestExpertCaps caps, String name) {
        return tuneDriver(new RemoteWebDriver(this.gridUrl, caps.get()), name);
    }

    private AppiumDriver createAndroidDriver(TestExpertCaps caps, String name) {
        return (AppiumDriver) tuneDriver(new AndroidDriver(this.gridUrl, caps.get()), name);
    }

    private AppiumDriver createIosDriver(TestExpertCaps caps, String name) {
        return (AppiumDriver) tuneDriver(new IOSDriver(this.gridUrl, caps.get()), name);
    }

    /**
     * Final tuning for a driver
     * <p>
     * Setting a file detector for a driver
     * and setting a driver name to SeleniumHolder
     *
     * @param driver - instance of a RemoteWebDriver
     * @param name   - driver name
     * @return driver with LocalFileDetector as a fileDetector
     */
    private RemoteWebDriver tuneDriver(RemoteWebDriver driver, String name) {
        DriverHelper.setDriverName(name);
        driver.setFileDetector(new LocalFileDetector());
        return driver;
    }

    private WebDriver throwException(String browserName, String platformName) {
        throw new RuntimeException("Not supported browser: " + browserName + ", for platform: " + platformName);
    }
}