package com.wisebits.tests;

import com.wisebits.POM.MainPageW3C;
import com.wisebits.TestBase;
import org.junit.Assert;
import org.junit.Test;

import static com.wisebits.PageURL.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TableCheckTest extends TestBase {

    @Test

    public void test()
    {
       _driver.get(_mainUrl);

        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести все строки таблицы Customers");
        mainPageW3C = mainPageW3C.clearCommandInTextAreaCodeSQL();
        mainPageW3C.sendCommandInTextAreaCodeSQL("'SELECT * FROM Customers;'");

        System.out.println("2. Проверить, что в выводе есть значения Giovanni Rovelli, Via Ludovico il Moro 22");
        assertThat(mainPageW3C.getTextForTableValue()).as("Address для ContactName Giovanni Rovelli не Via Ludovico il Moro 22")
                .isEqualTo("Via Ludovico il Moro 22");
    }



}
