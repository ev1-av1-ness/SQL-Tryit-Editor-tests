package com.wisebits.tests;

import com.wisebits.POM.MainPageW3C;
import com.wisebits.TestBase;
import org.junit.Test;

import java.util.stream.Stream;

import static com.wisebits.PageURL.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TableCheckTest extends TestBase {

    @Test
    public void getTheAddressOfContactTest() {
        System.out.println("Проверить, что значения полей для одной записи соответствуют ожидаемым");
        String contactNameValue = "Giovanni Rovelli";
        String addressValue = "Via Ludovico il Moro 22";

        String sqlCommandWords = "'SELECT * FROM Customers'";
        String[] sqlCommandWordsArray = {"'SELECT '", "'* '", "' FROM'", "' Customers'"};


        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести все строки таблицы Customers");
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL(sqlCommandWords, sqlCommandWordsArray);

        System.out.println("2. Проверить, что в выводе значению " + contactNameValue + " соответствует " +  addressValue);
        assertThat(mainPageW3C.getTextForTableValue(contactNameValue)).as("Значение Address для ContactName не соответствует ожидаемому")
                .isEqualTo(addressValue);
    }

    @Test
    public void checkForAmountOfRecordsTest() {
        System.out.println("Проверить соответствие количества записей в таблице ожидаемому по запросу");
        int amountOfRecords = 6;
        String sqlCommandWords = "'SELECT * FROM Customers WHERE City = \"London\"'";
        String[] sqlCommandWordsArray = {"'SELECT '", "'* '", "' FROM'", "' Customers'", "' WHERE'", "' City ='", "' \"London\"'"};

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести строки таблицы Customers по условию");
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL(sqlCommandWords, sqlCommandWordsArray);

        System.out.println("2. Проверить, что в выводе по условию количество записей равно " + amountOfRecords);

        _soft.assertThat(mainPageW3C.textNumberOfRecordsFromTip()).as("Количество записей в сообщении не соответствует ожидаемому")
                .contains(Integer.toString(amountOfRecords));
        _soft.assertThat(mainPageW3C.countAmountOfRecords()).as("Количество записей в таблице не соответствует ожидаемому")
                .isEqualTo(amountOfRecords);


        //опционально можно добавить проверку, что все элементы последнего столбца City - London
    }

    @Test
    public void checkForOneNewAddedRecordTest() {
        System.out.println("Проверить, что новая запись добавилась в таблицу");
        String sqlCommandWords = "'INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) " +
                "VALUES (\"Drasfublut Ikalfend\", \"Zigmund\", \"Yaren 26\", \"Berlin\", \"123843\", \"Germany\")'";
        String[] sqlCommandWordsArray = {"'INSERT '", "' '", "' INTO'", "' Customers (CustomerName, ContactName, Address, City, PostalCode, Country)'" +
                "' VALUES', "};

        _driver.get(_mainUrl);
        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        //проверить количество записей в таблице - было 91
        //Check message "You have made changes to the database. Rows affected: 1"
        //количество записей в таблице - стало 92
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
