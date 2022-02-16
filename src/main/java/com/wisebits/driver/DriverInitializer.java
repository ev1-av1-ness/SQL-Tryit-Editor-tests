package com.wisebits.driver;


import java.io.IOException;

public class DriverInitializer
{
    public static void initDriver() throws IOException
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver");
    }

}
