package com.example.javacalenderproject;
import com.example.javacalenderproject.database.Task;

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
