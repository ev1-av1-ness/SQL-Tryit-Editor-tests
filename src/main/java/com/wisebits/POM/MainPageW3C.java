package com.wisebits.POM;

import com.wisebits.PageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageW3C extends PageObject
{
    @FindBy(css = ".CodeMirror-line:nth-last-child(2) span > span:nth-last-of-type(1)")
    private WebElement _textareaCodeSQL;

    @FindBy(css = "span .cm-m-sql")
    private WebElement _commandWord;

    @FindBy(css = ".ws-btn")
    private WebElement _runSQLButton;

//    @FindBy(css = ".CodeMirror-line:nth-last-child(2)")
//    private WebElement _prelastlineCodeSQL;


    public MainPageW3C(WebDriver driver, WebDriverWait wait)
    {
        super(driver, wait);
        PageFactory.initElements(_driver, this);
    }


    private WebElement getTextareaCodeSQL() {
        return _textareaCodeSQL;
    }

//    private WebElement getCommandWord() {
//        return _commandWord;
//    }

    //private WebElement getPrelastlineCodeSQL() { return _prelastlineCodeSQL; }

    private WebElement getRunSQLButton() {
        return _runSQLButton;
    }


//    public int numberOfCommandWords() {
//        return _driver.findElements(By.cssSelector("span .cm-m-sql")).size();
//    }

    public MainPageW3C clearCommandInTextAreaCodeSQL() {
        System.out.println("Delete commands inside text area");

        JavascriptExecutor js = (JavascriptExecutor) _driver;
        List<WebElement> commandWords = _driver.findElements(By.cssSelector("span .cm-m-sql"));
        commandWords.stream().
                    filter(commandWord -> commandWord.getText().length() > 1).
                    forEach(commandWord -> js.executeScript("arguments[0].innerText = ''", commandWord));

//        while (numberOfCommandWords() > 1) {
//            try {
////                if (getTextareaCodeSQL().getText().length() > 0) {
//                    customClearInput(getCommandWord());
////                }
//            } catch (WebDriverException e) {
//                e.printStackTrace();
//            }
//            success = true;
//        }
        //Assert.assertTrue(("Attempt to clear text area is failed"), success);
        return this;
    }

    public MainPageW3C sendCommandInTextAreaCodeSQL(String sqlCommand) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("arguments[0].innerText = " + sqlCommand, getTextareaCodeSQL());
        getRunSQLButton().click();
        return this;
    }

    //получить из таблицы разные значения
//    public MainPageW3C checkTableValues() {
//        return this;
//    }
}
