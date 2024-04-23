package com.example.javacalenderproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javacalenderproject.database.TaskDatabase;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.List;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    // Declare the MaterialCalendarView and Calendar objects.
    MaterialCalendarView calendarView;
    java.util.Calendar calendar;
    public static TaskDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);
        database = TaskDatabase.getDatabase(getApplicationContext());

        // Initialize the calendarView by finding it in the layout.
        calendarView = findViewById(R.id.calenderView);

        // Initialize tableLayout by finding it from tablelayout.xml.
        //TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Get an instance of the current calendar.
        calendar = Calendar.getInstance();

        // Set an initial date for the calendar view.
        //setDate(1, 1, 2024);

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
}
/**
 public void setDate(int day, int month, int year) {
 // Set the calendar to the specified year, month, and day.
 calendar.set(Calendar.YEAR, year);
 // Subtract 1 from month because Calendar.MONTH is 0-based.
 calendar.set(Calendar.MONTH, month - 1);
 calendar.set(Calendar.DAY_OF_MONTH, day);

 // Set the current date of the MaterialCalendarView to reflect the changes.
 calendarView.setCurrentDate(calendar);
 }
 }
 **/
/**
 //@Override
 //protected void onCreate(Bundle SavedInstanceState) {
 //super.onCreate(SavedInstanceState);
 //setContentView(R.layout.activity_main);


 //btnGoCalendar = (Button) findViewById(R.id.goToCalendar);

 // create onclick listener for button (when clicked go to calendar)
 //btnGoCalendar.setOnClickListener(new View.OnClickListener() {
 //@Override
 // public void onClick(View v) {
 Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
 startActivity(intent);
 }
 });
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


 } **/


