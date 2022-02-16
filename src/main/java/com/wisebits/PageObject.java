package com.wisebits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageObject
{
    protected WebDriver _driver;
    protected WebDriverWait _wait;

    public PageObject(WebDriver driver, WebDriverWait wait)
    {
        _driver = driver;
        _wait = wait;
    }
}
