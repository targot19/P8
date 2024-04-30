package com.example.javacalenderproject.model;

import com.example.javacalenderproject.database.TaskPlanned;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
