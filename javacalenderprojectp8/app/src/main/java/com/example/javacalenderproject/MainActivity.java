package com.example.javacalenderproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

// Importing MaterialCalendarView and its associated classes.
import com.example.javacalenderproject.database.TaskDatabase;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    // Declare the MaterialCalendarView and Calendar objects.
    MaterialCalendarView calendarView;
    java.util.Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);

        // Initialize the database
        database = TaskDatabase.getDatabase(getApplicationContext());

        btnCreateTask = findViewById(R.id.btnCreateNewTask);

        // Initialize the calendarView by finding it in the layout.
        calendarView = findViewById(R.id.calenderView);

        // Initialize tableLayout by finding it from tablelayout.xml.
        //TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Get an instance of the current calendar.
        calendar = Calendar.getInstance();

        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Call the method to create a new task when the button is clicked
                createTask("Sample Task", "Icon", "Color", 30);
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onClick(View v) {
                // Start the CalendarActivity when the calendar button is clicked
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                // Extract the year, month, and day from the selected date.
                int year = date.getYear();
                // Add 1 to month since CalendarDay months are 0-based (January is 0).
                int month = date.getMonth() + 1;
                int day = date.getDay();

                // Display a toast message showing the selected date.
                Toast.makeText(MainActivity.this, day + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        }
    });
    }}
