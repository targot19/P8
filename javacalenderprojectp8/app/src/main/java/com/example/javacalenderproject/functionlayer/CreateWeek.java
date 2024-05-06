package com.example.javacalenderproject.functionlayer;

import static com.example.javacalenderproject.MainActivity.database;
import android.widget.TextView;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.HourlyPrice;
import com.example.javacalenderproject.model.TimeSlot;
import com.example.javacalenderproject.model.Week;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class CreateWeek {
    public static void loadWeekDates(List<LocalDate> weekDates, Week week) {
        for (int i = 0; i <7; i++) {
            for (int j = 0; j < 24; j++) {
                LocalDateTime date = weekDates.get(i).atTime(j,0);
                week.getTimeSlots()[i][j].setDate(date);
            }
        }
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

    public static void loadWeekPrices (List<HourlyPrice> weekPrices, Week week) {
        for (HourlyPrice hourPrice: weekPrices) {
            // get ints representing day of week and hour of day from data of the HourlyPrice object

            int dayOfweek = hourPrice.getDate().getDayOfWeek().getValue() -1; // zero indexing dayOfWeek by subtracting 1
            int hourOfDay = hourPrice.getHour();

            // get timeslot using day and hour
            TimeSlot timeSlot = week.getTimeSlots()[dayOfweek][hourOfDay];

            // set color of timeslot after price-color logic
            String color = null;
            if (hourPrice.getPrice() <= 0.4) {
                color = "green";
            }
            /** else if (hourPrice.getPrice() >= 0.2 && hourPrice.getPrice() < 0.4) {
                color = "greenyellow";
            } **/
            else if (hourPrice.getPrice() > 0.4 && hourPrice.getPrice() < 0.8) {
                color = "yellow";
            }
            /**else if (hourPrice.getPrice() >= 0.6 && hourPrice.getPrice() <= 0.8) {
                color = "orange";
            } **/
            else if (hourPrice.getPrice() >= 0.8) {
                color = "red";
            }
            timeSlot.setColor(color);

            // set date of timeslot
            timeSlot.setDate(hourPrice.getDate());
        }
    }

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

    public static List<HourlyPrice> getWeekPrices (List<LocalDate> weekDays, HourlyPrice[] priceData) {

        // Evt. indsæt kode der finder alle timepriser fra database afhængig af løsning.

        List<HourlyPrice> weekPrices = new ArrayList<>();

        for (HourlyPrice hourPrice: priceData) {
            // iterate all days in the week
            for (LocalDate weekDate: weekDays) {
                // if date of weekday matches date of hourPrice: add hourPrice object to list of week prices
                LocalDate temp = hourPrice.getDate().toLocalDate();
                if (temp.equals(weekDate)) {
                    weekPrices.add(hourPrice);
                }
            }
        }

        return weekPrices;
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
}
