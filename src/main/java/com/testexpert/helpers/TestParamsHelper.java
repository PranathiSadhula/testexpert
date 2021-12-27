package com.testexpert.helpers;

import org.openqa.selenium.remote.SessionId;

public class TestParamsHelper {

	private static ThreadLocal<String> testName = new ThreadLocal<>();
	private static ThreadLocal<String> platform = new ThreadLocal<>();
	private static ThreadLocal<String> browser = new ThreadLocal<>();
	private static ThreadLocal<String> nodeIP = new ThreadLocal<>();
	private static ThreadLocal<Integer> chromePort = new ThreadLocal<>();
	private static ThreadLocal<SessionId> sessionId = new ThreadLocal<>();

	public static String getTestName() {
		return testName.get();
	}

	public static void setTestName(String testName) {
		TestParamsHelper.testName.set(testName);
	}

	public static String getPlatform() {
		return platform.get();
	}

	public static void setPlatform(String platform) {
		TestParamsHelper.platform.set(platform);
	}

	public static String getBrowser() {
		return browser.get();
	}

	public static void setBrowser(String browser) {
		TestParamsHelper.browser.set(browser);
	}

	public static String getNodeIP() {
		return nodeIP.get();
	}

	public static void setNodeIP(String nodeIP) {
		TestParamsHelper.nodeIP.set(nodeIP);
	}

	public static Integer getChromePort() {
		return chromePort.get();
	}

	public static void setChromePort(Integer chromePort) {
		TestParamsHelper.chromePort.set(chromePort);
	}

	public static SessionId getSessionId() {
		return sessionId.get();
	}

	public static void setSessionId(SessionId sessionId) {
		TestParamsHelper.sessionId.set(sessionId);
	}
}