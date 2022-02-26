package com.wisebits.POM;

import com.wisebits.PageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainPageW3C extends PageObject {
    //    @FindBy(css = ".CodeMirror-line:nth-last-child(2) span > span:nth-last-of-type(1)")
//    private WebElement _textareaCodeSQL;
    @FindBy(xpath = "//div[contains(@class, 'CodeMirror-code')]")
    private WebElement _textareaCodeSQL;

    @FindBy(css = ".ws-btn")
    private WebElement _runSQLButton;

    @FindBy(xpath = "//div[contains(text(),'Number of Records:')]") //другой локатор!
    private WebElement _numberOfRecordsFromTip;


    public MainPageW3C(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(_driver, this);
    }


    private WebElement getTextareaCodeSQL() {
        return _textareaCodeSQL;
    }

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
        System.out.println("Delete existing command inside text area");

        _driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Actions actions = new Actions(_driver);
        boolean success = false;

        while (!getTextareaCodeSQL().getText().equals(""))
            try {
                actions.click().sendKeys(getTextareaCodeSQL(), Keys.END);
                    for (int i = 0; i <= 10; i++) {
                    actions.sendKeys(getTextareaCodeSQL(), Keys.BACK_SPACE);
                    }
                actions.build().perform();
                success = true;
            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
            }
        Assert.assertTrue(("Attempt to clear text area is failed"), success);
        return this;
    }

    public MainPageW3C clickRunSQLButton() {
        getRunSQLButton().click();
        return this;
    }

    public MainPageW3C sendCommandInTextAreaCodeSQL(String sqlCommandWords) {
        System.out.println("Type command inside text area and run");
        _driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Actions actions = new Actions(_driver);

        actions.click().sendKeys(getTextareaCodeSQL(), sqlCommandWords).build().perform();

        clickRunSQLButton();
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
