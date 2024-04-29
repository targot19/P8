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
        this.date = null;
        hour = -1;
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

    public void clearData() {
        tasks.clear();
        deleteDate();
        setColor("noColor");
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
        setHour();
    }

    public void deleteDate() {date = null;}

    public void setHour() {
        if (date != null) {
            hour = date.getHour();
        }
       else {hour = -1;}
    }

    public LocalDateTime getDate() {return date; }
}
