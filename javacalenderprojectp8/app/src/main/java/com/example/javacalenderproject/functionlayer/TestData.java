package com.example.javacalenderproject.functionlayer;

import android.util.Log;

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

    public static HourlyPrice[] getTestPriceData () {
        HourlyPrice[] testPriceData = new HourlyPrice[24*7];
        int dayCount = 0;
        int index = 0;
        for (int i = 0; i < 7; i++) {
            dayCount = i;
            for (int j = 0; j < 24; j++) {
                double price = 0.4;
                if (dayCount > 3) {price = 1.5;}
                int dayOfMonth = 1+i;
                int hourOfDay = j;
                LocalDateTime localDateTime = LocalDateTime.of(2024,5, dayOfMonth,hourOfDay,1);
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                testPriceData[index] = new HourlyPrice(price, date);
                index++;
            }
        }
        // Log each individual price
        for (HourlyPrice hourlyPrice : testPriceData) {
            Log.d("TestData", "Hourly Price: " + hourlyPrice.getPrice() + ", Full Date: " + hourlyPrice.getDate());
            Log.d("TestData",  "Hour: " + hourlyPrice.getHour());
        }

        return testPriceData;
    }
}
