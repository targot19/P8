package com.example.javacalenderproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.room.Room;
import androidx.room.TypeConverters;

// Annotation to specify the entities for the database and its version
@Database(entities = {Task.class, TaskPlanned.class}, version = 4, exportSchema = false)
// Annotation to specify the converters for the database
@TypeConverters({Converters.class})
public abstract class TaskDatabase extends RoomDatabase {
    // Abstract method to get the DAO for Task
    public abstract TaskDAO taskDAO();
    // Abstract method to get the DAO for TaskPlanned
    public abstract TaskPlannedDAO taskPlannedDAO();

    // Singleton instance of the database
    private static volatile TaskDatabase INSTANCE;

    // Method to get the singleton instance of the database
    public static TaskDatabase getDatabase(final Context context) {
        // Check if the instance is null
        if (INSTANCE == null) {
            // Synchronize the block to prevent multiple threads from creating multiple instances
            synchronized (TaskDatabase.class) {
                // Check again if the instance is null
                if (INSTANCE == null) {
                    // Build the database instance
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDatabase.class, "tasks")
                            // If the schema has changed and we didn't define a migration strategy, destroy and recreate the database
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        // Return the instance of the database
        return INSTANCE;
    }
}