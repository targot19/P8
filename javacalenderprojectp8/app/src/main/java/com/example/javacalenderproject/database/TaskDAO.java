package com.example.javacalenderproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.javacalenderproject.model.Task;

import java.util.List;

@Dao
public interface TaskDAO {

    // method for creating tasks
    @Query("INSERT INTO taskTable (taskType) VALUES ('New task')")
    void create();

    // method for getting all the tasks in the database
    @Query("SELECT * FROM taskTable")
    List<Task> getAllTasks();

    // method for saving tasks to database
    @Query("UPDATE taskTable SET taskType = :taskName WHERE id = :id")
    void save(String taskName, int id);

    @Insert
    void insertTask(Task task);

}
