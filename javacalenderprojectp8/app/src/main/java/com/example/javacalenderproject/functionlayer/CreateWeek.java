package com.example.javacalenderproject.functionlayer;

import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.Week;

public class CreateWeek {
    public static Week createTestWeek() {
        // TEST WeekTableAdapter:
        // create week
        Week testWeek = new Week(1);

        // set colors
        testWeek.getTimeSlots()[2][7].setColor("yellow");
        testWeek.getTimeSlots()[3][7].setColor("yellow");
        testWeek.getTimeSlots()[0][7].setColor("green");
        testWeek.getTimeSlots()[1][7].setColor("green");
        /*
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 12; j++) {
                testWeek.getTimeSlots()[i][j].setColor("yellow");
            }
            for (int k = 12; k < 24; k++) {
                testWeek.getTimeSlots()[i][k].setColor("red");
            }
        }
        */
        // set TaskPlanned for test
        TaskPlanned testTask = new TaskPlanned("Støvsuge");
        testWeek.getTimeSlots()[0][7].addTask(testTask);

        return testWeek;
    }
}
