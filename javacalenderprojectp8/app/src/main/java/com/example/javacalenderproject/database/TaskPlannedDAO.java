package com.example.javacalenderproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskPlannedDAO {
        @Insert
        void insert(TaskPlanned taskPlanned);

        @Query("SELECT * FROM planned_tasks")
        List<TaskPlanned> getAllPlannedTasks();

        @Query("DELETE FROM planned_tasks WHERE taskName = :taskName")
        void delete(String taskName);
    }





