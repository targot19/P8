package com.example.javacalenderproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// Importing MaterialCalendarView and its associated classes.
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    // create button
    private Button btnGoCalendar;
    private Button btnCreateTask;
    public static TaskDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database (Consider moving this to a background thread or application class in production)
        database = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "tasks")
                .allowMainThreadQueries() // This should be avoided; use background threads instead.
                .build();

        btnGoCalendar = findViewById(R.id.goToCalendar);
        btnCreateTask = findViewById(R.id.btnCreateTask); // Make sure this ID matches your layout

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
        // Construct a new Task object
        Task newTask = new Task(name, icon, color, duration);

        // Insert the task into the database on a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.taskDAO().insertTask(newTask);
            }
        }).start();
    }
    }

    // MOVED TO CALENDAR CLASS
    /*
    // Declare the MaterialCalendarView and Calendar objects.
    MaterialCalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);


        // Initialize the calendarView by finding it in the layout.
        calendarView = findViewById(R.id.calenderView);
        // Get an instance of the current calendar.
        calendar = Calendar.getInstance();

        // Set an initial date for the calendar view.
        setDate(1, 1, 2024);

        // Set a listener to handle changes when a user selects a date on the calendar.
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                // Extract the year, month, and day from the selected date.
                int year = date.getYear();
                // Add 1 to month since CalendarDay months are 0-based (January is 0).
                int month = date.getMonth() + 1;
                int day = date.getDay();

                // Display a toast message showing the selected date.
                Toast.makeText(MainActivity.this, day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDate(int day, int month, int year) {
        // Set the calendar to the specified year, month, and day.
        calendar.set(Calendar.YEAR, year);
        // Subtract 1 from month because Calendar.MONTH is 0-based.
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        // Set the current date of the MaterialCalendarView to reflect the changes.
        calendarView.setCurrentDate(calendar);
    } */

