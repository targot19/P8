package com.example.javacalenderproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;



@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();

    private static volatile TaskDatabase INSTANCE;

    static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "task_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

