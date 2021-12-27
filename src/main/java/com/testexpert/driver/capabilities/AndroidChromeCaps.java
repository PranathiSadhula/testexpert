package com.testexpert.driver.capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * Caps for Chrome browser on Android
 * @author Pranathi.sadhula
 *
 */
public class AndroidChromeCaps extends TestExpertCaps {

    /**
     * @param customCaps This is desiredCapabilities to be set
     */
    public AndroidChromeCaps(DesiredCapabilities customCaps) {
        super(customCaps);
    }

    
    /**
     *implementation of abstract method 
     *{@link com.testexpert.driver.capabilities.TestExpertCaps#get()}
     */
    public DesiredCapabilities get() {
        DesiredCapabilities caps = getAndroidChromeCaps();
        if (!this.customCaps.asMap().isEmpty()) {
            caps.merge(this.customCaps);
        }
        return caps;
    }

    /**
     * @return DesiredCapabilities for Android Chrome browser settings
     */
    private DesiredCapabilities getAndroidChromeCaps() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("newCommandTimeout", "900");
        caps.setPlatform(Platform.ANDROID);
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        return caps;
    }
}

