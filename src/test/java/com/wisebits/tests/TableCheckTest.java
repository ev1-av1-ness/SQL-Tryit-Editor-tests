package com.wisebits.tests;

import com.wisebits.POM.MainPageW3C;
import com.wisebits.TestBase;
import org.junit.Test;

import static com.wisebits.PageURL.*;

public class TableCheckTest extends TestBase {

    @Test

    public void test()
    {
       _driver.get(_mainUrl);

        MainPageW3C mainPageW3C = new MainPageW3C(_driver, _wait);

        System.out.println("1. Вывести все строки таблицы Customers");
        mainPageW3C = mainPageW3C.clearAndSendCommandInTextAreaCodeSQL();
        System.out.println("fdgdg");



    }



}
