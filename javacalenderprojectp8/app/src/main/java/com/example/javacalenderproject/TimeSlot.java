package com.example.javacalenderproject;

import android.os.Build;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TimeSlot {
    LocalDateTime date;
    int hour;
    private String color;
    private ArrayList<TaskPlanned> tasks;

    TimeSlot() {
        // CHANGE DATE - til hvad?
        this.date = LocalDateTime.now();
        hour = date.getHour();
        this.color = "noColor";
        tasks = new ArrayList<TaskPlanned>();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<TaskPlanned> getTasks() {
        return tasks;
    }

    public void addTask(TaskPlanned newTask) {
        tasks.add(newTask);
    }

    public void setDate(LocalDateTime date) {this.date = date; }

    public LocalDateTime getDate() {return date; }
}
