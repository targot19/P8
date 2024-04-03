package com.example.javacalenderproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "taskTable")
public class Task {

    // create attributes
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "taskType")
    public String taskName;

    //private String taskIcon;
    private String taskColor;
    private int taskDuration;

    private String taskIcon;

    private ArrayList<familyMember> assignedFamilyMembers;

    // create constructor
    public Task(String name, String icon, String color, int duration) {
        this.taskName = name;
        //this.taskIcon = icon;
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
    public void setIcon(String icon) {this.taskIcon = icon;}

    // @param color
    public void setColor(String color) {
        this.taskColor = color;
    }

    public void assignTask(familyMember familyMember) {

        assignedFamilyMembers.add(familyMember);

    }

    public void unassignTask(familyMember familyMember) {

        assignedFamilyMembers.remove(familyMember);

    }
    private List<Task> tasks = new ArrayList<>();

    public void reload() {
        tasks = MainActivity.database.taskDAO().getAllTasks();
        notifyDataSetChanged();
    }
}
