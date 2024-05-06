package com.example.javacalenderproject.functionlayer;

import static com.example.javacalenderproject.MainActivity.database;

import android.util.Log;

import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.HourlyPrice;

import java.time.LocalDateTime;
import java.util.List;

public class CreateTaskPlanned {
    // Method to create a new task and add it to the database directly
    public static void createTask(String name, LocalDateTime date) {
        TaskPlanned newTask = new TaskPlanned(name, date);
        new Thread(() -> {
            try {
                database.taskPlannedDAO().insert(newTask);
                List<TaskPlanned> tasks = database.taskPlannedDAO().getAllPlannedTasks();
                Log.d("MainActivity", "Task inserted, total tasks now: " + tasks.size());
                for (TaskPlanned task : tasks) {
                    Log.d("MainActivity", "Task: " + task.getTaskName());
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Failed to insert or retrieve tasks", e);
            }
        }).start();
    }

    public static void priceToDatabase(HourlyPrice[] priceData) {
        new Thread(() -> {
            try {
                for (HourlyPrice hourlyPrice : priceData) {
                    database.HourlyPriceDAO().insert(hourlyPrice);
                }
                List<HourlyPrice> prices = database.HourlyPriceDAO().getAllPrices();
                Log.d("MainActivity", "Prices inserted, total prices now: " + prices.size());
                for (HourlyPrice price : prices) {
                    Log.d("MainActivity", "Price: " + price.getPrice());
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Failed to insert or retrieve prices", e);
            }
        }).start();
    }


    }


