package com.example.javacalenderproject.functionlayer;

import com.example.javacalenderproject.model.HourlyPrice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.Date;

public class TestData {
    public static void createTestData () {
        // CREATE TESTDATA IN DATABASE
        // for current week

        CreateTaskPlanned.createTask("TestTaskCopenhagen: now()", ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")).toLocalDateTime());
        CreateTaskPlanned.createTask("T1: 25.04 kl.01.01", LocalDateTime.of(2024,4,25,1,1));
        CreateTaskPlanned.createTask("T2: now()+1 dag", ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")).toLocalDateTime().plusDays(1));
        CreateTaskPlanned.createTask("T3: now()+1 dag", ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")).toLocalDateTime().plusDays(2));
        CreateTaskPlanned.createTask("T4: now()+1 dag", ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")).toLocalDateTime().plusDays(3));

        // for week 13
        CreateTaskPlanned.createTask("W13T1",LocalDateTime.of(2024,3,25,0,1));
        CreateTaskPlanned.createTask("W13T2",LocalDateTime.of(2024,3,25,1,1));
        CreateTaskPlanned.createTask("W13T3",LocalDateTime.of(2024,3,27,7,1));
        CreateTaskPlanned.createTask("W13T4",LocalDateTime.of(2024,3,28,7,1));
        CreateTaskPlanned.createTask("W13T5",LocalDateTime.of(2024,3,31,23,1));
        CreateTaskPlanned.createTask("W13T5",LocalDateTime.of(2024,3,31,22,1));

    }

    public static HourlyPrice[] createPriceTestData () {
        HourlyPrice[] testPriceData = new HourlyPrice[24*7];
        int dayCount = 0;

        for (int i = 0; i < 7; i++) {
            dayCount = i;
            for (int j = 0; j < 24; j++) {
                int index = i+1*j+1;
                double price = 0.4;
                int dayOfMonth = 1+i;
                int hourOfDay = 1+j;
                //Date date = Date.of(2024,5, dayOfMonth,hourOfDay,1);
                testPriceData[index] = new HourlyPrice(price, new Date());
            }
        }

        return testPriceData;

    }

}
