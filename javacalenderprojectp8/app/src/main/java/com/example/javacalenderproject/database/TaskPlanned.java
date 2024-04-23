package com.example.javacalenderproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity(tableName = "planned_tasks")
public class TaskPlanned {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskName")
    private String taskName;

    @ColumnInfo(name = "taskColor")
    private String taskColor;

    @ColumnInfo(name = "taskDuration")
    private int taskDuration;

    @ColumnInfo(name = "taskIcon")
    private String taskIcon;

    @ColumnInfo(name = "taskAssignedMembers")
    private ArrayList<FamilyMember> assignedFamilyMembers;

    // OBS database date + hour
    //LocalDateTime date;
    //int hour;

    // Public no-arg constructor required by Room
    public TaskPlanned() {
    }
    public TaskPlanned(String name) {
        this.taskName = name;
        //this.date = null;
        // get hour from date to set hour
        taskDuration = 0;
    }
    TaskPlanned(String name, LocalDateTime date) {
        this.taskName = name;
        //this.date = date;
        // get hour from date to set hour
        taskDuration = 0;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }

    public String getTaskIcon() {
        return taskIcon;
    }

    public void setTaskIcon(String taskIcon) {
        this.taskIcon = taskIcon;
    }

    public ArrayList<FamilyMember> getAssignedFamilyMembers() {
        return assignedFamilyMembers;
    }

    public void setAssignedFamilyMembers(ArrayList<FamilyMember> assignedFamilyMembers) {
        this.assignedFamilyMembers = assignedFamilyMembers;
    }
    /*
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }*/
}


