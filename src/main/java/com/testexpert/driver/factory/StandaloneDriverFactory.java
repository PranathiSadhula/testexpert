package com.testexpert.driver.factory;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.testexpert.driver.capabilities.ChromeCaps;
import com.testexpert.driver.capabilities.EdgeCaps;
import com.testexpert.driver.capabilities.FireFoxCaps;
import com.testexpert.driver.capabilities.GeckoCaps;
import com.testexpert.driver.capabilities.IECaps;
import com.testexpert.helpers.DriverHelper;
import com.testexpert.helpers.TestParamsHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory to create driver for a single-threaded usage
 */
public class StandaloneDriverFactory implements DriverFactory {

    private final String browserName;
    private final String platformName;
    private final DesiredCapabilities customCaps;
    private final UnexpectedAlertBehaviour alertBehaviour = UnexpectedAlertBehaviour.ACCEPT;
    private final boolean isHeadless;
    @SuppressWarnings("unused")
	private final URL gridUrl;

    StandaloneDriverFactory(String browserName, String platformName, DesiredCapabilities customCaps, boolean isHeadless, final URL gridUrl) {
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
            default: {
                return throwException(browserName, platformName);
            }
        }
    }

    private WebDriver linuxDriver() {
        TestParamsHelper.setPlatform(LINUX);
        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return chrome(customCaps, Platform.LINUX);
            }
            case FIREFOX: {
                return firefox(customCaps, Platform.LINUX);
            }
            case GECKO: {
                return gecko(customCaps, Platform.LINUX);
            }
            default: {
                return throwException(browserName, LINUX);
            }
        }
    }

    private WebDriver macDriver() {
        TestParamsHelper.setPlatform(MAC);
        switch (browserName.toLowerCase().trim()) {
            case CHROME: {
                return chrome(customCaps, Platform.MAC);
            }
            case FIREFOX: {
                return firefox(customCaps, Platform.MAC);
            }
            case GECKO: {
                return gecko(customCaps, Platform.MAC);
            }
          
            default: {
                return throwException(browserName, MAC);
            }
        }
    }

    private WebDriver windowsDriver() {
        TestParamsHelper.setPlatform(WINDOWS);
        switch (browserName.toLowerCase().trim()) {
            case FIREFOX: {
                return firefox(customCaps, Platform.WINDOWS);
            }
            case GECKO: {
                return gecko(customCaps, Platform.WINDOWS);
            }
            case CHROME: {
                return chrome(customCaps, Platform.WINDOWS);
            }
            case EDGE: {
                return edge(customCaps);
            }
            case IE: {
                return ie(customCaps);
            }
            default: {
                return throwException(browserName, WINDOWS);
            }
        }
    }

    private WebDriver throwException(String browserName, String platformName) {
        throw new RuntimeException("Not supported browser: " + browserName + ", for platform: " + platformName);
    }

    private WebDriver firefox(DesiredCapabilities customCaps, Platform platform) {
        DriverHelper.setDriverName(FIREFOX);
        return new FirefoxDriver(
                new FireFoxCaps(customCaps, this.alertBehaviour, platform).get()
        );
    }

    private WebDriver gecko(DesiredCapabilities customCaps, Platform platform) {
        DriverHelper.setDriverName(GECKO);
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(
                new GeckoCaps(customCaps, this.alertBehaviour, platform).get()
        );
    }

    private WebDriver chrome(DesiredCapabilities customCaps, Platform platform) {
        DriverHelper.setDriverName(CHROME);
        WebDriverManager.chromedriver().setup();
        ChromeDriverService service = ChromeDriverService.createDefaultService();
        ChromeDriver chromeDriver = new ChromeDriver(
                service,
                new ChromeCaps(customCaps, this.alertBehaviour, this.isHeadless, platform).get()
        );
        TestParamsHelper.setChromePort(service.getUrl().getPort());
        return chromeDriver;
    }

    private WebDriver ie(DesiredCapabilities customCaps) {
        DriverHelper.setDriverName(IE);
        WebDriverManager.iedriver().browserVersion(STABLE_IE_DRIVER_VERSION).setup();
        return new InternetExplorerDriver(
                new IECaps(customCaps, "", this.alertBehaviour).get());
    }

    private WebDriver edge(DesiredCapabilities customCaps) {
        DriverHelper.setDriverName(EDGE);
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(
                new EdgeCaps(customCaps, this.alertBehaviour).get());
    }

  
}