package com.testexpert.driver.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

/**
 * Base abstraction representing capabilities for driver initialization
 * all caps should implement a support for custom capabilities that could be passed
 * from the projects to provide custom configurations for particular browser
 *
 * @author Pranathi.sadhula
 *
 */
public abstract class TestExpertCaps {
    /**
     * final customCaps
     */
    final DesiredCapabilities customCaps;

    /**
     * @param customCaps This is desiredCapabilities to be set
     */
    public TestExpertCaps(DesiredCapabilities customCaps) {
        this.customCaps = customCaps;
    }

    /**
     * abstract method to be implement by subclass implementations 
     * @see com.testexpert.driver.capabilities.ChromeCaps
     * @see com.testexpert.driver.capabilities.FireFoxCaps
     * @return MutableCapabilities
     */
    public abstract MutableCapabilities get();

    
    /**
     * @param options is MutableCapabilities to be set with loggingPreferences
     */
    void setLoggingPrefs(MutableCapabilities options) {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    }
}
