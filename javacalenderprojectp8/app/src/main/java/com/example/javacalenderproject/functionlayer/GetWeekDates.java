package com.example.javacalenderproject.functionlayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class GetWeekDates {

    // method returns list of the days of the week from the given weeknumber and year
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

}
