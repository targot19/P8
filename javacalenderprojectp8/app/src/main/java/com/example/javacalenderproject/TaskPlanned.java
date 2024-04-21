package com.example.javacalenderproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getTaskColor() { return taskColor; }
    public void setTaskColor(String taskColor) { this.taskColor = taskColor; }

    public int getTaskDuration() { return taskDuration; }
    public void setTaskDuration(int taskDuration) { this.taskDuration = taskDuration; }

    public String getTaskIcon() { return taskIcon; }
    public void setTaskIcon(String taskIcon) { this.taskIcon = taskIcon; }

    public ArrayList<familyMember> getAssignedFamilyMembers() { return assignedFamilyMembers; }
    public void setAssignedFamilyMembers(ArrayList<familyMember> assignedFamilyMembers) { this.assignedFamilyMembers = assignedFamilyMembers; }
}
