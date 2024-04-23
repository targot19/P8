package com.example.javacalenderproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.javacalenderproject.familyMember;

import java.util.ArrayList;

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
    private ArrayList<familyMember> assignedFamilyMembers;

    // Public no-arg constructor required by Room
    public TaskPlanned() {
    }

    // Getters and setters
import com.example.javacalenderproject.FamilyMember;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskPlanned {
    int id;
    String name;
    LocalDateTime date;
    int hour;
    int duration;
    ArrayList<FamilyMember> assignee;

    TaskPlanned(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        // get hour from date to set hour
        duration = 0;
    }
    public TaskPlanned(String name) {
        this.name = name;
        this.date = null;
        // get hour from date to set hour
        duration = 0;
    }

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

    public ArrayList<familyMember> getAssignedFamilyMembers() {
        return assignedFamilyMembers;
    }

    public void setAssignedFamilyMembers(ArrayList<familyMember> assignedFamilyMembers) {
        this.assignedFamilyMembers = assignedFamilyMembers;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<FamilyMember> getAssignee() {
        return assignee;
    }

    public void setAssignee(ArrayList<FamilyMember> assignee) {
        this.assignee = assignee;
    }
}
