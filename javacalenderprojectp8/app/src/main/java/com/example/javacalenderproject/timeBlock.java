package com.example.javacalenderproject;
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
        * for loop, 24 times
        * for every loop, set hours +1
        * next timeblock
        *
        *  */

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
