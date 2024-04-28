package com.example.javacalenderproject.functionlayer;

public class WeekNumberTest {

            public void runTest() {
            // Create an instance of WeekNumberHelper
            WeekNumberHelper weekNumberHelper = new WeekNumberHelper();

            // Log initial values
            System.out.println("Initial values:");
            System.out.println("Displayed Week Number: " + weekNumberHelper.getDisplayedWeekNumber());
            System.out.println("Start Date: " + weekNumberHelper.getStartDate());
            System.out.println("End Date: " + weekNumberHelper.getEndDate());

            // Increment the week number and log the new values
            weekNumberHelper.incrementWeekNumber();
            System.out.println("\nAfter incrementing:");
            System.out.println("Displayed Week Number: " + weekNumberHelper.getDisplayedWeekNumber());
            System.out.println("Start Date: " + weekNumberHelper.getStartDate());
            System.out.println("End Date: " + weekNumberHelper.getEndDate());

            // Decrement the week number and log the new values
            weekNumberHelper.decrementWeekNumber();
            System.out.println("\nAfter decrementing:");
            System.out.println("Displayed Week Number: " + weekNumberHelper.getDisplayedWeekNumber());
            System.out.println("Start Date: " + weekNumberHelper.getStartDate());
            System.out.println("End Date: " + weekNumberHelper.getEndDate());
        }
}
