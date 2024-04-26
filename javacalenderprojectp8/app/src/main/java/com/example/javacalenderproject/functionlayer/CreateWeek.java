package com.example.javacalenderproject.functionlayer;

import static com.example.javacalenderproject.MainActivity.database;

import androidx.room.Database;

import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.Week;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class CreateWeek {

    public static List<TaskPlanned> getWeekTasks (List<LocalDate> weekDays) {
        // get list of all planned tasks
        List<TaskPlanned> allTasks = database.taskPlannedDAO().getAllPlannedTasks();

        // create new list to hold tasks for specified days
        List<TaskPlanned> weekTasks = new ArrayList<>();

        // TEST
        /*
        LocalDateTime date1 = LocalDateTime.of(2024, 4, 25, 1,1 );
        LocalDateTime date2 = LocalDateTime.of(2024, 4, 25, 1, 1);
        TaskPlanned tAsK = new TaskPlanned("taaaaask", date1);
        allTasks.add(tAsK);
         */


        // loop through list of all planned tasks and add the ones where date variable matches the week days
        // iterate all planned tasks
        /*
        for (int i = 0; i < allTasks.size(); i ++) {
            for (int j = 0; j < weekDays.size(); j++) {
                if (allTasks.get(i).getDate().toLocalDate().equals(weekDays.get(j))) {
                    weekTasks.add(allTasks.get(i));
                }
            }
        }
         */


        for (TaskPlanned task: allTasks) {
            // iterate all days in the week
            for (LocalDate weekDate: weekDays) {
                // if date of weekday matches date of task: add task to list of week tasks
                LocalDate temp = task.getDate().toLocalDate();
                if (temp.equals(weekDate)) {
                    weekTasks.add(task);
                }
            }
        }



        return weekTasks;
        //return allTasks;
    }



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

    // method creates new week with no data
    // OBS: Currently returns week with testdata (not empty week)
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

        // TEST DATA
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
