package com.example.javacalenderproject.functionlayer;

import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.Week;

public class CreateWeek {
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
