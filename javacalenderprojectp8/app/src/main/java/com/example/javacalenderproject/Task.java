package com.example.javacalenderproject;

import java.util.ArrayList;

public class Task {

    // create attributes
    private String taskName;
    private String taskIcon;
    private String taskColor;
    private int taskDuration;

    /**
     * Not quite sure about this one
    public ArrayList Arraylist<familyMember>;
     **/

    // create constructor
    public Task(String name, String icon, String color, int duration) {
        this.taskName = name;
        this.taskIcon = icon;
        this.taskColor = color;
        this.taskDuration = duration;
    }

    /** create methods for Task
     * with these methods, it is possible to retrieve the private attributes
     **/

    // @param name
    public void setName(String name) {
        this.taskName = name;
    }

    // @param icon
    public void setIcon(String icon) {
        this.taskIcon = icon;
    }

    // @param color
    public void setColor(String color) {
        this.taskColor = color;
    }
}
