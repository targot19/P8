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

    public TaskTemplate() {
    }
    // create constructor


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
    private List<TaskTemplate> taskTemplates = new ArrayList<>();

    public void setTaskTemplates(List<TaskTemplate> taskTemplates) {
        this.taskTemplates = taskTemplates;
    }

    public List<TaskTemplate> getTaskTemplates() {
        return taskTemplates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
