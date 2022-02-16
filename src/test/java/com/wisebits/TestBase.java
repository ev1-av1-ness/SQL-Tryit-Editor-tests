package com.wisebits;

import com.wisebits.driver.DriverInitializer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase extends TestWatcher
{
    protected WebDriver _driver;
    protected WebDriverWait _wait;


    @BeforeClass
    public static void BeforeClass() throws IOException {
        DriverInitializer.initDriver();
    }

    @Before
    public void Before() {
        _driver = new ChromeDriver();

        if (_driver != null) {
            _driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            _driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            _wait = new WebDriverWait(_driver, 30);
        }
    }

    @After
    public void After() {
        if(_driver!=null) {
            _driver.close();
            _driver.quit();
        }
    }
}
