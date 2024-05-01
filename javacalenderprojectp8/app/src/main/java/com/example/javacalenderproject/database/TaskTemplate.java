package com.example.javacalenderproject.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.javacalenderproject.MainActivity;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "TaskTemplateTable")
public class TaskTemplate {

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
    private ArrayList<FamilyMember> assignedFamilyMembers;

    public TaskTemplate() {
    }
    // create constructor
    public TaskTemplate(String name, String icon, String color, int duration) {
        this.taskName = name;
        //this.taskIcon = icon;
        this.taskColor = color;
        this.taskDuration = duration;
    }



    public TaskTemplate(String taskName){

        this.taskName = taskName;

    }


    public void insertTask(TaskTemplate taskTemplate) {
        MainActivity.database.taskDAO().insertTask(taskTemplate);
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

    public ArrayList<FamilyMember> getAssignedFamilyMembers() {
        return assignedFamilyMembers;
    }

    public void setAssignedFamilyMembers(ArrayList<FamilyMember> assignedFamilyMembers) {
        this.assignedFamilyMembers = assignedFamilyMembers;
    }

    public void assignTask(FamilyMember familyMember) {

        assignedFamilyMembers.add(familyMember);

    }

    public void unassignTask(FamilyMember familyMember) {

        assignedFamilyMembers.remove(familyMember);

    }
    private List<TaskTemplate> taskTemplates = new ArrayList<>();

    public void setTaskTemplates(List<TaskTemplate> taskTemplates) {
        this.taskTemplates = taskTemplates;
    }

    public List<TaskTemplate> getTaskTemplates() {
        return taskTemplates;
    }

    public List<TaskTemplate> getTasks() {
        return taskTemplates;
    }

    // Setter for the tasks list
    public void setTasks(List<TaskTemplate> taskTemplates) {
        this.taskTemplates = taskTemplates;
    }

    public void reload() {
        taskTemplates = MainActivity.database.taskDAO().getAllTasks();
    }
}
