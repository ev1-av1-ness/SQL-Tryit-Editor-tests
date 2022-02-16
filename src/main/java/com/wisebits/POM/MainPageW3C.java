package com.wisebits.POM;

import com.wisebits.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageW3C extends PageObject
{
   @FindBy(id = "sdksdf")
   private WebElement _jdsjd;

   @FindBy(id = "sjljkjkdf")
   private WebElement _dsf;


    public MainPageW3C(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
        PageFactory.initElements(_driver, this);
    }
}
