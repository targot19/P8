package com.example.javacalenderproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;
import androidx.room.TypeConverters;


@Database(entities = {Task.class, TaskPlanned.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
    public abstract TaskPlannedDAO taskPlannedDAO();

    private static volatile TaskDatabase INSTANCE;

    static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "tasks")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

