package com.example.javacalenderproject.functionlayer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestData {
    public static void createTestData () {
        // CREATE TESTDATA IN DATABASE
        // for current week
        CreateTaskPlanned.createTask("TestTask", ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")).toLocalDateTime());
        CreateTaskPlanned.createTask("T2", LocalDateTime.of(2024,4,25,1,1));
        CreateTaskPlanned.createTask("T3",LocalDateTime.now());
        CreateTaskPlanned.createTask("T4",LocalDateTime.now());
        CreateTaskPlanned.createTask("T5",LocalDateTime.now());

        // for week 13
        CreateTaskPlanned.createTask("W13T1",LocalDateTime.of(2024,3,25,0,1));
        CreateTaskPlanned.createTask("W13T2",LocalDateTime.of(2024,3,25,1,1));
        CreateTaskPlanned.createTask("W13T3",LocalDateTime.of(2024,3,27,7,1));
        CreateTaskPlanned.createTask("W13T4",LocalDateTime.of(2024,3,28,7,1));
        CreateTaskPlanned.createTask("W13T5",LocalDateTime.of(2024,3,31,23,1));
        CreateTaskPlanned.createTask("W13T5",LocalDateTime.of(2024,3,31,22,1));


    }
}
