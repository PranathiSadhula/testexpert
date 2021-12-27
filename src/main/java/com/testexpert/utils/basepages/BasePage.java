package com.testexpert.utils.basepages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;




/**
 * @author Pranathi.sadhula
 *
 */
/**
 * @author Pranathi.sadhula
 *
 */
/**
 * @author Pranathi.sadhula
 *
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Wait<WebDriver> fluentwait;
    
    public final void open(final String url) {
        if (!url.isEmpty()) {
            driver.get(url);
        }
    }

    public void init(WebDriver driver) {
        this.driver = driver;
        fluentwait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(50)).pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        initFindByAnnotations(this);
        init();
    }

    protected void init() {
    }

    protected void handleRedirect() {
    }

    private <E extends BasePage> void initFindByAnnotations(final E page) {
        PageFactory.initElements(driver, page);
    }

 
    public  void add() {
    	
    }

   
}