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

    protected void customClearUsingBackspace(WebElement element) throws InterruptedException
    {
        _driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        Instant timeout = Instant.now().plusSeconds(30);

        System.out.println("try to clear element with backspace: " + element);

        while (timeout.compareTo(Instant.now()) > 0 && element.getAttribute("value").length() > 0) {
            try {
                element.sendKeys(Keys.CONTROL + "a");
                element.sendKeys(Keys.BACK_SPACE);

            } catch (WebDriverException ex) {
                Thread.sleep(400);
            }
        }

        returnDefaultImplicitlyWait();
        Assert.assertEquals(("Unsuccessful attempt to clear element"), 0, element.getAttribute("value").length());
    }
}
