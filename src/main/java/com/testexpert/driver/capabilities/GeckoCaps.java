package com.testexpert.driver.capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver.Capability;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Caps for Gecko browser
 * @author Pranathi.sadhula
 */
public class GeckoCaps extends TestExpertCaps {

    private final UnexpectedAlertBehaviour alertBehaviour;
    private final Platform platform;

    public GeckoCaps(DesiredCapabilities customCaps, UnexpectedAlertBehaviour alertBehaviour, Platform platform) {
        super(customCaps);
        this.alertBehaviour = alertBehaviour;
        this.platform = platform;
    }
    /**
     *implementation of abstract method {@link com.testexpert.driver.capabilities.TestExpertCaps.get()}
     */
    public FirefoxOptions get() {
        FirefoxOptions caps = getGeckoOptions();
        if (!this.customCaps.asMap().isEmpty()) {
            caps.merge(this.customCaps);
        }
        return caps;
    }

    /**
     * @return FirefoxOptions
     */
    private FirefoxOptions getGeckoOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, this.alertBehaviour);
        options.setCapability(Capability.MARIONETTE, true);
        options.setCapability("platform", platform);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        setLoggingPrefs(options);
        return options;
    }
}
