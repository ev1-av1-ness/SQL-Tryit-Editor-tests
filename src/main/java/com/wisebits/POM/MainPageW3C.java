package com.wisebits.POM;

import com.wisebits.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageW3C extends PageObject
{
   @FindBy(xpath = "//div[@class='CodeMirror-code']//*[@class='cm-m-sql']")
   private WebElement _textareaCodeSQL;

   @FindBy(id = "sjljkjkdf")
   private WebElement _dsf;


    public MainPageW3C(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
        PageFactory.initElements(_driver, this);
    }

    private WebElement getTextareaCodeSQL() {
        return _textareaCodeSQL;
    }


    public MainPageW3C clearAndSendCommandInTextAreaCodeSQL() {
        //System.out.println("Ввести команду SQL в поле выполнения команд");
        System.out.println("Кликнуть в поле ввода и очистить его");
        getTextareaCodeSQL().click();
        return this;
    }
}
