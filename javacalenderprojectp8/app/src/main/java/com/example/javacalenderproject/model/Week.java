package com.example.javacalenderproject.model;

import java.time.LocalDate;
import java.util.List;

import android.util.Log;

// flyt Week klassen til function eller ui layer?
public class Week {
    // weekNumber variabel overflødig? bruges ikke til noget
    private int weekNumber;
    private TimeSlot[][] timeSlots;

    public Week(int weekNumber) {
        this.weekNumber = weekNumber;
        initializeTimeSlots();
    }
    public Week() {
        initializeTimeSlots();
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public TimeSlot[][] getTimeSlots() {
        return timeSlots;
    }

    private void initializeTimeSlots () {
        timeSlots = new TimeSlot[7][24];
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 24; j++) {
                timeSlots[i][j] = new TimeSlot();
            }
        }
    }

    // method to clear data of all timeslots
    public void clearWeek () {
        for (int i = 0; i < timeSlots.length; i++) {
            for (int j = 0; j < timeSlots[0].length; j++ ) {
                timeSlots[i][j].clearData();
            }
        }
        Log.d("Update Test", "clearWeek executed");
    }


}
