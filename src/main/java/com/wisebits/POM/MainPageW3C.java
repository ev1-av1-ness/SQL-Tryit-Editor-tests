package com.wisebits.POM;

import com.wisebits.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageW3C extends PageObject
{
    @FindBy(css = ".CodeMirror-line:nth-last-child(2) span > span:nth-last-of-type(1)")
    private WebElement _textareaCodeSQL;

    @FindBy(css = ".CodeMirror-line:nth-last-child(2)")
    private WebElement _prelastlineCodeSQL;

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

    private WebElement getPrelastlineCodeSQL() { return _prelastlineCodeSQL; }


    public MainPageW3C clearCommandInTextAreaCodeSQL() {
        System.out.println("Кликнуть в поле ввода и очистить его");
        getTextareaCodeSQL().click();

//        if(getTextareaCodeSQL().getAttribute("value").length() > 0) {
//
//            customClearInput(getPrelastlineCodeSQL());
//            }
        //и если первая строчка не пустая, то повторить
        //пока _textareaCodeSQL; не останется 1 единственный
        //можно через коллекцию size()
        return this;
    }

    public MainPageW3C sendCommandInTextAreaCodeSQL(String sqlCommand) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("arguments[0].innerText = " + sqlCommand, getTextareaCodeSQL());
        //getTextareaCodeSQL().sendKeys(sqlCommand);
        return this;
    }

    //получить из таблицы разные значения
}
