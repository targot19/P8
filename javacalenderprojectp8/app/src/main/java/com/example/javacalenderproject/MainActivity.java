package com.example.javacalenderproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.example.javacalenderproject.database.Task;
import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.database.TaskPlanned;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Create buttons
    private Button btnGoCalendar;
    private Button btnCreateTask;
    private Button btnCreatePlannedTask;
    public static TaskDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database
        database = TaskDatabase.getDatabase(getApplicationContext());

        // Initialize buttons
        btnGoCalendar = findViewById(R.id.goToCalendar);
        btnCreateTask = findViewById(R.id.btnCreateNewTask);
        btnCreatePlannedTask = findViewById(R.id.btnCreateNewPlannedTask);

        // Set button listeners
        btnCreatePlannedTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPlannedTask("Sample Planned Task", "Icon Placeholder", "Color Placeholder", 60);
            }
        });
        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createTask("Sample Task", "Icon", "Color", 30);
            }
        });
        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method for planned task creation
    private void createPlannedTask(String name, String icon, String color, int duration) {
        TaskPlanned newPlannedTask = new TaskPlanned();
        newPlannedTask.setTaskName(name);
        newPlannedTask.setTaskIcon(icon);
        newPlannedTask.setTaskColor(color);
        newPlannedTask.setTaskDuration(duration);

        new Thread(() -> {
            database.taskPlannedDAO().insert(newPlannedTask);
            Log.d("MainActivity", "Planned Task inserted successfully.");
        }).start();
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
    }
}
