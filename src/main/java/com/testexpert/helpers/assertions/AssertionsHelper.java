package com.testexpert.helpers.assertions;

import org.testng.asserts.SoftAssert;

public class AssertionsHelper {

	private static final ThreadLocal<SoftAssert> softAssertHolder = new ThreadLocal<>();

	public static SoftAssert softAssert() {
		return softAssertHolder.get();
	}

	public static void setSoftAssert(SoftAssert softAssert) {
		AssertionsHelper.softAssertHolder.set(softAssert);
	}
}
