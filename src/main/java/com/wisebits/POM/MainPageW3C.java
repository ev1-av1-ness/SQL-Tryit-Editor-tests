package com.wisebits.POM;

import com.wisebits.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPageW3C extends PageObject
{
//    @FindBy(css = ".CodeMirror-line:nth-last-child(2) span > span:nth-last-of-type(1)")
//    private WebElement _textareaCodeSQL;
    @FindBy(xpath = "//textarea[@id='textareaCodeSQL']")
    private WebElement _textareaCodeSQL;

    @FindBy(css = ".ws-btn")
    private WebElement _runSQLButton;

    @FindBy(xpath = "//div[contains(text(),'Number of Records:')]")
    private WebElement _numberOfRecordsFromTip;


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

    private WebElement getNumberOfRecordsFromTip() {
        return _numberOfRecordsFromTip;
    }


    public int countAmountOfRecords() {
        return _driver.findElements(By.xpath("//div[@id='divResultSQL']//table//tr[position()>1]")).size();
    }

    public MainPageW3C clearCommandInTextAreaCodeSQL() {
        System.out.println("Delete commands inside text area");

        JavascriptExecutor js = (JavascriptExecutor) _driver;
        List<WebElement> commandWords = _driver.findElements(By.cssSelector("span .cm-m-sql"));
        commandWords.stream()
                    .filter(commandWord -> commandWord.getText().length() > 1)
                    .forEach(commandWord -> js.executeScript("arguments[0].innerText = ''", commandWord));

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

    public MainPageW3C sendCommandInTextAreaCodeSQL(String sqlCommandWords, String[] sqlCommandWordsArray) {

        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("arguments[0].innerText = " + sqlCommandWords, getTextareaCodeSQL());

        int amountOfWords = sqlCommandWordsArray.length;
            for (int i = 0; i < amountOfWords; i++) {
                List<WebElement> commandWordsSpans = _driver.findElements(By.cssSelector("span .cm-m-sql"));
                js.executeScript("arguments[0].innerText = " + sqlCommandWordsArray[i], commandWordsSpans.get(i));
        }

        getRunSQLButton().click();
        return this;
    }

    public String getAddressOfContactName(WebElement contactNameValue) {
            String addressValue = contactNameValue.findElement(By.xpath("following-sibling::td[1]")).getText();
            return addressValue;
    }

    //получить из таблицы значение
    //todo отрефачить
    public String getTextForTableValue(String contactNameValue) {
//
        List<WebElement> valuesOfContactNamesColumn = _driver.findElements(By.xpath("//tbody//td[3]"));
        List<String> address = valuesOfContactNamesColumn.stream()
                    .filter(contactName -> contactName.getText().contains(contactNameValue))
                    .map(contactName -> getAddressOfContactName(contactName))
                    .collect(Collectors.toList());
        return address.get(0);
    }

    public String textNumberOfRecordsFromTip() {
        return getNumberOfRecordsFromTip().getText();
    }

    public Boolean isMissingTableOfRecords() {
        return isElementMissing(By.xpath("//div[@id='divResultSQL']//table"));
    }
}
