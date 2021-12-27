package com.testexpert.helpers;

import static com.testexpert.helpers.DriverHelper.getDriver;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureReportListener implements ITestListener {
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }




    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Method '" + getTestMethodName(iTestResult) + "' is Failed");
        if (getDriver() != null) {
            System.out.println("Screenshot has captured for the Test Method '" + getTestMethodName(iTestResult) + "'");
         //   saveScreenshotPNG(getDriver());
           Allure.addAttachment("Failured ScreenShot", new ByteArrayInputStream( saveScreenshotPNG(getDriver())));
            
        }
    }

  
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
