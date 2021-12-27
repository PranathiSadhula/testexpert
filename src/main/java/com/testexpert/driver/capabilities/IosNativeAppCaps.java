package com.testexpert.driver.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.testexpert.driver.factory.DriverFactory;

/**
 * Caps for Chrome browser on Android
 */
public class IosNativeAppCaps extends TestExpertCaps {

    public IosNativeAppCaps(DesiredCapabilities customCaps) {
        super(customCaps);
    }
    /**
     *implementation of abstract method {@link com.testexpert.driver.capabilities.TestExpertCaps.get()}
     */
    public DesiredCapabilities get() {
        DesiredCapabilities caps = new DesiredCapabilities();
        if (!this.customCaps.asMap().isEmpty()) {
            caps.merge(this.customCaps);
        }
        if (this.customCaps.getBrowserName().equals(DriverFactory.NATIVE_APP)) {
            this.customCaps.setBrowserName(DriverFactory.EMPTY);
        }
        return caps;
    }
}
