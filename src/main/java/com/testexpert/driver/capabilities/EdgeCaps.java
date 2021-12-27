package com.testexpert.driver.capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Caps for Edge browser
 * @author Pranathi.sadhula
 */
public class EdgeCaps extends TestExpertCaps {

    /**
     * final UnexpectedAlertBehaviour to be set on constructor call {@link com.testexpert.driver.capabilities.EdgeCaps.EdgeCaps(DesiredCapabilities, UnexpectedAlertBehaviour)}
     */
    private final UnexpectedAlertBehaviour alertBehaviour;

    public EdgeCaps(DesiredCapabilities customCaps, UnexpectedAlertBehaviour alertBehaviour) {
        super(customCaps);
        this.alertBehaviour = alertBehaviour;
    }
    /**
     *implementation of abstract method {@link com.testexpert.driver.capabilities.TestExpertCaps.get()}
     */
    public EdgeOptions get() {
        EdgeOptions caps = getEdgeOptions();
        if (!this.customCaps.asMap().isEmpty()) {
            caps.merge(this.customCaps);
        }
        return caps;
    }

    /**
     * @return EdgeOptions
     */
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setCapability("platform", Platform.WINDOWS);
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, this.alertBehaviour);
        setLoggingPrefs(options);
        return options;
    }
}
