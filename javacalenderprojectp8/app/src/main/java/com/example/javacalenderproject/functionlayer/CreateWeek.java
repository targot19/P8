package com.example.javacalenderproject.functionlayer;

import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.Week;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class CreateWeek {
    public static List<LocalDate> GetWeekDates (int weekOfYear, int year) {

        WeekFields weekFields = WeekFields.ISO;

        // Get the date at the start of the specified week in the given year
        //LocalDate startOfWeek = LocalDate.ofYearDay(2024, 1).with(weekFields.weekOfYear(), weekOfYear).with(weekFields.dayOfWeek(),1); // Move to the first day of the week
        LocalDate startOfWeek = LocalDate.ofYearDay(year,1) // Start from January 1st
                .with(weekFields.weekOfYear(), weekOfYear) // Adjust to the week number
                .with(weekFields.dayOfWeek(), 1); // Move to the first day of the week

        // Convert to LocalDateTime (with midnight time for start of the day)
        //LocalDate startOfWeekDateTime = startOfWeek.atStartOfDay();

        // Generate all dates in the week
        // change to LocalDate objects?
        List<LocalDate> weekDates = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate tempDate = startOfWeek.plusDays(i); // add days to get the whole week
            weekDates.add(tempDate);
        }
        return weekDates;
    }

    public static Week emptyWeek() {
        // TEST WeekTableAdapter:
        // create week
        Week emptyWeek = new Week(1);

        return emptyWeek;
    }
    public static Week createTestWeek() {
        // TEST WeekTableAdapter:
        // create week
        Week testWeek = new Week(1);

        // set colors
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 12; j++) {
                testWeek.getTimeSlots()[i][j].setColor("yellow");
            }
            for (int k = 12; k < 24; k++) {
                testWeek.getTimeSlots()[i][k].setColor("red");
            }
        }
        testWeek.getTimeSlots()[2][7].setColor("yellow");
        testWeek.getTimeSlots()[3][7].setColor("yellow");
        testWeek.getTimeSlots()[0][7].setColor("green");
        testWeek.getTimeSlots()[1][7].setColor("green");
        testWeek.getTimeSlots()[1][9].setColor("red");

        // set TaskPlanned for test
        TaskPlanned task1 = new TaskPlanned("Støvsuge");
        TaskPlanned task2 = new TaskPlanned("Flæskesteg");
        TaskPlanned task3 = new TaskPlanned("Tøjvask");
        testWeek.getTimeSlots()[0][7].addTask(task1);
        testWeek.getTimeSlots()[0][7].addTask(task2);
        testWeek.getTimeSlots()[0][7].addTask(task3);

        testWeek.getTimeSlots()[6][23].addTask(task1);
        testWeek.getTimeSlots()[1][9].addTask(task2);
        testWeek.getTimeSlots()[2][9].addTask(task1);
        testWeek.getTimeSlots()[3][9].addTask(task3);

        return testWeek;
    }
}
