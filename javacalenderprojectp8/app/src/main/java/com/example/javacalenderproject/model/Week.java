package com.example.javacalenderproject.model;

import android.util.Log;

public class Week {
    private TimeSlot[][] timeSlots;

    public Week(int weekNumber) {
        initializeTimeSlots();
    }
    public Week() {
        initializeTimeSlots();
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
