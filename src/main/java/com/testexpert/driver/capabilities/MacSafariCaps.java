package com.testexpert.driver.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

public class MacSafariCaps extends TestExpertCaps {
    public MacSafariCaps(DesiredCapabilities customCaps) {
        super(customCaps);
    }

    /**
     *implementation of abstract method {@link com.testexpert.driver.capabilities.TestExpertCaps.get()}
     */
    @Override
    public MutableCapabilities get() {
        return new SafariOptions().merge(customCaps);
    }
}
