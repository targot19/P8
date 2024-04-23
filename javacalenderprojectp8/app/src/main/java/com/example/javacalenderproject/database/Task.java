package com.example.javacalenderproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.javacalenderproject.MainActivity;
import com.example.javacalenderproject.familyMember;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "taskTable")
public class Task {

    // create attributes
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "taskType")
    public String taskName;


    @ColumnInfo(name = "taskColor")
    private String taskColor;
    @ColumnInfo(name = "taskDuration")
    private int taskDuration;
    @ColumnInfo(name = "taskIcon")
    private String taskIcon;
    @ColumnInfo(name = "taskAssignedMembers")
    private ArrayList<familyMember> assignedFamilyMembers;



    public Task() {
    }
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
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    // Getter and setter for taskColor
    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    // Getter and setter for taskDuration
    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }

    // Getter and setter for taskIcon
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
    }

    public void assignTask(familyMember familyMember) {

        assignedFamilyMembers.add(familyMember);

    }

    public void unassignTask(familyMember familyMember) {

        assignedFamilyMembers.remove(familyMember);

    }
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    // Setter for the tasks list
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void reload() {
        tasks = MainActivity.database.taskDAO().getAllTasks();

    }
}
