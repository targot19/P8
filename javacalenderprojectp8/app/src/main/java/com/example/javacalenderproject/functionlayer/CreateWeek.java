package com.example.javacalenderproject.functionlayer;

import static com.example.javacalenderproject.MainActivity.database;
import android.widget.TextView;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.TimeSlot;
import com.example.javacalenderproject.model.Week;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class CreateWeek {

    public static void loadWeekTasks(List<TaskPlanned> weekTasks, Week week) {
        for (TaskPlanned task: weekTasks) {
            // zero indexing dayOfWeek by subtracting 1
            int dayOfweek = task.getDate().getDayOfWeek().getValue() -1;
            int hourOfDay = task.getHour();
            TimeSlot timeSlot = week.getTimeSlots()[dayOfweek][hourOfDay];
            timeSlot.addTask(task);
        }
    }

    // create time data for row header -- better to move to adapter?
    public static List<String> getTimeIntervals() {
        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String tempString = i + ".00";
            if (i <10) { tempString = "0" + tempString;}
            timeList.add(tempString);
        }
        return timeList;
    }

    public static void setCalendarView(ArrayList<TextView> dateViews, TextView weekView, TextView monthView, List<LocalDate> weekDates, int weeknumber) {

        // set dates
        for (int i = 0; i < 7; i++){
            dateViews.get(i).setText(""+weekDates.get(i).getDayOfMonth());
        }
        // set week number
        weekView.setText("Uge "+weeknumber);
        // set month
        monthView.setText(""+weekDates.get(0).getMonth());
    }

    public static List<TaskPlanned> getWeekTasks (List<LocalDate> weekDays) {
        // get list of all planned tasks
        List<TaskPlanned> allTasks = database.taskPlannedDAO().getAllPlannedTasks();

        // create new list to hold tasks for specified days
        List<TaskPlanned> weekTasks = new ArrayList<>();

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
    }

    // method returns list of the days of the week from the given weeknumber and year
    public static List<LocalDate> getWeekDates (int weekOfYear, int year) {

        WeekFields weekFields = WeekFields.ISO;

        // Get the date of the first day of the specified week in the given year
        LocalDate startOfWeek = LocalDate.ofYearDay(year,1) // Start from January 1st
                .with(weekFields.weekOfYear(), weekOfYear) // Adjust to the week number
                .with(weekFields.dayOfWeek(), 1); // Move to the first day of the week

        List<LocalDate> weekDates = new ArrayList<>();
        // from date of the first day of the week: get dates of remaining 6 days
        for (int i = 0; i < 7; i++) {
            LocalDate weekDate = startOfWeek.plusDays(i);
            weekDates.add(weekDate);
        }
        return weekDates;
    }


    // TEST returns week with testdata (not empty week)
    public static Week createTestWeek() {

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
