package com.example.javacalenderproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskDAO.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
