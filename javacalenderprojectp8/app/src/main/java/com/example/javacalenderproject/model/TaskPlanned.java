package com.example.javacalenderproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity(tableName = "planned_tasks")
public class TaskPlanned {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskName")
    private String taskName;

    // OBS database date + hour
    //@TypeConverters({Converters.class})
    @ColumnInfo(name = "taskDateTime")
    private LocalDateTime date;

    // Public no-arg constructor required by Room
    public TaskPlanned() {
    }
    public TaskPlanned(String name) {
        this.taskName = name;
        this.date = null;
        // get hour from date to set hour

    }
    public TaskPlanned(String name, LocalDateTime date) {
        this.taskName = name;
        this.date = date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getHour() {
        return date.getHour();
    }


}


