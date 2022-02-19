package com.wisebits;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public abstract class PageObject
{
    protected WebDriver _driver;
    protected WebDriverWait _wait;
    private final Integer defaultImplicitlyWaitSeconds = 30;

    public PageObject(WebDriver driver, WebDriverWait wait)
    {
        _driver = driver;
        _wait = wait;
    }

    protected void returnDefaultImplicitlyWait()
    {
        _driver.manage().timeouts().implicitlyWait(defaultImplicitlyWaitSeconds, TimeUnit.SECONDS);
    }
}
