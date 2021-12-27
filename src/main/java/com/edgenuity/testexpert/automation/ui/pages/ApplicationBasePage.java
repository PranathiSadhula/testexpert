package com.edgenuity.testexpert.automation.ui.pages;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.testexpert.helpers.DriverHelper;
import com.testexpert.utils.basepages.BasePage;



public class ApplicationBasePage extends BasePage {

   protected <T extends ApplicationBasePage> T switchToDefaultContent(Class<T> classToReturnInstance) {
        DriverHelper.setDriver(DriverHelper.getDriver().switchTo().defaultContent());
        try {
            return classToReturnInstance.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    protected WebElement webElement(By xpath, int timeoutInSeconds, boolean failIfNotFound) {
        try {
            return new FluentWait<WebDriver>(DriverHelper.getDriver())
                    .withTimeout(Duration.of(timeoutInSeconds, SECONDS))
                    .pollingEvery(Duration.of(500, MILLIS))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated((xpath)));
        } catch (TimeoutException toe) {
            if (!failIfNotFound)
                return null;
            else
                throw toe;
        }
    }

    protected String executeJavaScript(String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHelper.getDriver();
        return (String) jsExecutor.executeScript(script);
    }

    protected String getCurrentFrame() {
        return executeJavaScript("return self.name");
    }


    protected <T extends ApplicationBasePage> T switchToFrame(By locator, Class<T> clazz) {
        WebElement element = webElement(locator, 100, false);
        if (element == null) {
            switchToDefaultContent(clazz);
            element = webElement(locator, 30, true);
        }
        return switchToFrame(element, clazz);
    }

    private <T extends ApplicationBasePage> T switchToFrame(WebElement element, Class<T> clazz) {

       fluentwait.until(ExpectedConditions.visibilityOf(element));
        String name = element.getAttribute("name");
        String currentFrame = getCurrentFrame();
        currentFrame = (currentFrame == null) ? "" : currentFrame;
        if (!currentFrame.equals(name)) {
            DriverHelper.setDriver(DriverHelper.getDriver().switchTo().frame(element));
        }
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
