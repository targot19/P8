package com.example.javacalenderproject.model;
import java.util.*;
public class timeBlock {

    private int hours;

    private int minutes;

    private ArrayList <Task> tasks;

    public void addTaskToTimeBlock(Task task) {

        tasks.add(task);

    }

    public void removeTaskFromTimeBlock(Task task) {

        tasks.remove(task);

    }
    public void setTimeBlock(int hours) {

        /* TODO
        *
        *  */
        int count = 0;
        for (int i = 0; i < 24; i++) {
            count += count;
            hours = count;
        }
    }

    public int getHours() {

        return hours;
    }


    public int getMinutes() {

        return minutes;

    }
    public timeBlock() {

        hours = 0;
        minutes = 0;

    }



}
