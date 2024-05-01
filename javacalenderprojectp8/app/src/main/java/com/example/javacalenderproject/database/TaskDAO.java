package com.example.javacalenderproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    // method for creating tasks
    @Query("INSERT INTO TaskTemplateTable (taskType) VALUES ('New task')")
    void create();

    // method for getting all the tasks in the database
    @Query("SELECT * FROM TaskTemplateTable")
    List<TaskTemplate> getAllTasks();

    // method for saving tasks to database
    @Query("UPDATE TaskTemplateTable SET taskType = :taskName WHERE id = :id")
    void save(String taskName, int id);

    @Insert
    void insertTask(TaskTemplate taskTemplate);

}
