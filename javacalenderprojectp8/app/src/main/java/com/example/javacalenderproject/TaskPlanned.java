package com.example.javacalenderproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "planned_tasks",
        foreignKeys = @ForeignKey(entity = Task.class,
                parentColumns = "id",
                childColumns = "task_id"))
                //onDelete = ForeignKey.CASCADE)) Dunno if we want
public class TaskPlanned {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskName")
    public String taskName;
    @ColumnInfo(name = "task_id")
    public int taskId;  // Foreign key to 'Task'

    @ColumnInfo(name = "taskColor")
    private String taskColor;
    @ColumnInfo(name = "taskDuration")
    private int taskDuration;
    @ColumnInfo(name = "taskIcon")
    private String taskIcon;
    @ColumnInfo(name = "taskAssignedMembers")
    private ArrayList<familyMember> assignedFamilyMembers;


    public TaskPlanned(Task task) {

        this.taskId = task.id;  // Linking to the original Task ID
        this.taskName = task.taskName;  // Duplicating task name
    }







}
