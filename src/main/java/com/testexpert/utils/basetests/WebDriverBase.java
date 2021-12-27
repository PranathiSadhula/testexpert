package com.testexpert.utils.basetests;

import static com.testexpert.driver.factory.WebDriverFactory.initDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.testexpert.utils.basepages.BasePage;
import com.testexpert.utils.basepages.PageLoader;

/**
 * @author Pranathi.sadhula
 *
 */
public class WebDriverBase {

	/**
	 * @param <T>
	 * @param page
	 * @return
	 */
	protected <T extends BasePage> T get(Class<T> page) {
		return PageLoader.get(page);
	}

	/**
	 * @param <T>
	 * @param page
	 * @param url
	 * @return
	 */
	protected <T extends BasePage> T get(Class<T> page, String url) {
		initDriver();
		return PageLoader.get(page, url);
	}

	/**
	 * @param <T>
	 * @param page
	 * @param url
	 * @param caps
	 * @return
	 */
	protected <T extends BasePage> T get(Class<T> page, String url, DesiredCapabilities caps) {
		initDriver(caps);
		return PageLoader.get(page, url);
	}

}
