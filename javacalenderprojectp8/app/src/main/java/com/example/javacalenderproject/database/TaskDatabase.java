package com.example.javacalenderproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.javacalenderproject.model.HourlyPrice;
import com.example.javacalenderproject.model.TaskPlanned;
import com.example.javacalenderproject.uilayer.TaskTemplate;

// changed version to 5
@Database(entities = {TaskTemplate.class, TaskPlanned.class, HourlyPrice.class}, version = 14, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
    public abstract TaskPlannedDAO taskPlannedDAO();
    public abstract HourlyPriceDAO HourlyPriceDAO();

    private static volatile TaskDatabase INSTANCE;

    public static TaskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "tasks")
                            .fallbackToDestructiveMigration()
                            // allow mainthreadqueries ---------
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

