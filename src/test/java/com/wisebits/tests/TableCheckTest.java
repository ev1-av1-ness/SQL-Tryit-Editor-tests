package com.wisebits.tests;

import com.wisebits.POM.MainPageW3C;
import com.wisebits.TestBase;
import org.junit.Test;

import java.util.stream.Stream;

import static com.wisebits.PageURL.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TableCheckTest extends TestBase {

    @Test
    public void getTheAddressOfContactTest()
    {
        System.out.println("Проверить, что значения полей для одной записи соответствуют ожидаемым");
        String contactNameValue = "Giovanni Rovelli";
        String addressValue = "Via Ludovico il Moro 22";
        String[] sqlCommandWords = {"'SELECT '", "'* '", "' FROM'", "' Customers'"};

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести все строки таблицы Customers");
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL(sqlCommandWords);

        System.out.println("2. Проверить, что в выводе значению " + contactNameValue + " соответствует " +  addressValue);
        assertThat(mainPageW3C.getTextForTableValue(contactNameValue)).as("Значение Address для ContactName не соответствует ожидаемому")
                .isEqualTo(addressValue);
    }

    @Test
    public void checkForAmountOfRecordsTest()
    {
        System.out.println("Проверить соответствие количества записей в таблице ожидаемому по запросу");
        int amountOfRecords = 6;
        //String[] sqlCommandWords = {"'SELECT '", "'* '", "' FROM'", "' Customers', """};
        String condition = "WHERE city = 'London'";

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести строки таблицы Customers, где " + condition);
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        //mainPageW3C.sendCommandInTextAreaCodeSQL("'SELECT * FROM Customers " + condition + ";'");

        System.out.println("2. Проверить, что в выводе по условию " + condition + " количество записей равно " + amountOfRecords);

        assertThat(mainPageW3C.textNumberOfRecordsFromTip()).as("Количество записей в сообщении не соответствует ожидаемому")
                .contains(Integer.toString(amountOfRecords));
        assertThat(mainPageW3C.countAmountOfRecords()).as("Количество записей в таблице не соответствует ожидаемому")
                .isEqualTo(amountOfRecords);


        //опционально можно добавить проверку, что все элементы последнего столбца City - London
    }

    @Test
    public void checkForOneNewAddedRecordTest() {
        System.out.println("Проверить, что новая запись добавилась в таблицу");
        String commandToAddRecord = "'INSERT ..";


        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        //проверить количество записей в таблице - было
        //количество записей в таблице - стало
        //можно softAssertion для каждого значения поля. собрать их в лист (и стримом), проверить на соответствие ожидаемым
        //expected List
    }

    @Test
    public void checkUpdatedValuesForRecordTest() {
        System.out.println("Проверить, что в записи обновились все поля");

        //применить randomValues - helper. Ограничить в хелпере рандом количеством записей в таблице

        int customerID = 7;
        String commandToUpdateRecord = "UPDATE .. WHERE customerID = " + customerID;

        //ввести команду


        //можно softAssertion для каждого значения поля. собрать их в лист (и стримом), проверить на соответствие ожидаемым
        //expected List
    }

    @Test
    public void checkResultsAreMissingAfterDeleted() {
        System.out.println("Проверить, что после удаления данные не выводятся в таблицу");
        String commandToDeleteRecords = "'DELETE FROM Customers WHERE City = \"London\";'";
        String commandToSelectDeletedRecords = "'SELECT * FROM Customers WHERE City = \"London\";'";

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        //mainPageW3C.sendCommandInTextAreaCodeSQL(commandToDeleteRecords);
        //Check message "You have made changes to the database. Rows affected: 6"

        //mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        //mainPageW3C.sendCommandInTextAreaCodeSQL(commandToSelectDeletedRecords);
        //Check message "No result."

        assertThat(mainPageW3C.isMissingTableOfRecords()).as("Таблица записей отображается").isTrue();
    }
}
