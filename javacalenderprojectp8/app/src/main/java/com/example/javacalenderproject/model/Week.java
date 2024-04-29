package com.example.javacalenderproject.model;

// weekNumber variable overflødig? bruges ikke til noget ..
// flyt Week klassen til function eller ui layer?
public class Week {
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

    public void clearWeek () {
        for (int i = 0; i < timeSlots.length; i++) {
            for (int j = 0; j < timeSlots[0].length; j++ ) {
                timeSlots[i][j].clearData();
            }
        }
    }

}
