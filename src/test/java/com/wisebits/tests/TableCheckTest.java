package com.wisebits.tests;

import com.wisebits.POM.MainPageW3C;
import com.wisebits.TestBase;
import org.junit.Test;

import static com.wisebits.PageURL.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TableCheckTest extends TestBase {

    @Test
    public void getTheAddressOfContactTest()
    {
        System.out.println("Проверить, что значения полей для одной записи соответствуют ожидаемым");
        String contactNameValue = "Giovanni Rovelli";
        String addressValue = "Via Ludovico il Moro 22";

       _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести все строки таблицы Customers");
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL("'SELECT * FROM Customers;'");

        System.out.println("2. Проверить, что в выводе значению " + contactNameValue + " соответствует " +  addressValue);
        assertThat(mainPageW3C.getTextForTableValue(contactNameValue)).as("Значение Address для ContactName не соответствует ожидаемому")
                .isEqualTo(addressValue);
    }

    @Test
    public void checkForAmountOfRecordsTest()
    {
        System.out.println("Проверить соответствие количества записей в таблице ожидаемому по запросу");
        int amountOfRecords = 6;
        String condition = "WHERE city = 'London'";

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести строки таблицы Customers, где " + condition);
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL("'SELECT * FROM Customers " + condition + ";'");

        System.out.println("2. Проверить, что в выводе по условию " + condition + " количество записей равно " + amountOfRecords);

        //.contains(amountOfRecords.toString());
        //countAmountOfRecords().equalsTo(amountOfRecords)
        //опционально еще проверка, что все элементы последнего столбца City - London
    }

    @Test
    public void checkForOneNewAddedRecordTest() {
        System.out.println("Проверить, что новая запись добавилась в таблицу");
        String commandToAddRecord = "'INSERT ..";

        //опционально применить randomValues

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);
    }

    @Test
    public void checkUpdatedValuesForRecordTest() {
        System.out.println("Проверить, что в записи обновились все поля");

        //применить randomValues - helper. Ограничить в хелпере рандом количеством записей в таблице
        int customerID = 7;
        String commandToUpdateRecord = "UPDATE .. WHERE customerID = " + customerID;
    }

    @Test
    public void checkResultsAreMissingAfterDeleted() {
        System.out.println("Проверить, что после удаления данные не выводятся в таблицу");

        //DELETE FROM Customers WHERE City = 'London';
        //Check message "You have made changes to the database. Rows affected: 6"

        //SELECT * FROM Customers WHERE City = 'London';
        //Check message "No result."
        //Check isMissing Element - //div[@id='divResultSQL']//table
    }
}
