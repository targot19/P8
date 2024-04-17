package com.example.javacalenderproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.util.Log;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    // create button
    private Button btnGoCalendar;
    private Button btnCreateTask;
    public static TaskDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database
        database = TaskDatabase.getDatabase(getApplicationContext());


        btnGoCalendar = findViewById(R.id.goToCalendar);
        btnCreateTask = findViewById(R.id.btnCreateNewTask);

        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Call the method to create a new task when the button is clicked
                createTask("Sample Task", "Icon", "Color", 30);
            }
        });

        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the CalendarActivity when the calendar button is clicked
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to create a new task
    private void createTask(String name, String icon, String color, int duration) {
        Task newTask = new Task(name, icon, color, duration);

        new Thread(() -> {
            try {
                database.taskDAO().insertTask(newTask);
                List<Task> tasks = database.taskDAO().getAllTasks();
                Log.d("MainActivity", "Task inserted, total tasks now: " + tasks.size());
                for (Task task : tasks) {
                    Log.d("MainActivity", "Task: " + task.getTaskName());
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Failed to insert or retrieve tasks", e);
            }
        }).start();

}}

