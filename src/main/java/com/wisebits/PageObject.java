package com.wisebits;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public abstract class PageObject
{
    protected WebDriver _driver;
    protected WebDriverWait _wait;
    private final Integer defaultImplicitlyWaitSeconds = 3;

    public PageObject(WebDriver driver, WebDriverWait wait)
    {
        _driver = driver;
        _wait = wait;
    }

    protected void returnDefaultImplicitlyWait()
    {
        _driver.manage().timeouts().implicitlyWait(defaultImplicitlyWaitSeconds, TimeUnit.SECONDS);
    }

    protected Boolean isElementMissing(By locator) {

        returnDefaultImplicitlyWait();

            try {
                _driver.findElement(locator);
                return false;
            } catch (org.openqa.selenium.NoSuchElementException e) {
                return true;
            }
        }
}

