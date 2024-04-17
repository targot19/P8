package com.example.javacalenderproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare the MaterialCalendarView and Calendar objects.
    MaterialCalendarView calendarView;
    java.util.Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);

        // Initialize the calendarView by finding it in the layout.
        calendarView = findViewById(R.id.calenderView);
        // Get an instance of the current calendar.
        calendar = Calendar.getInstance();

        ///TEST RECYCLERVIEW TASKS
        List<testTask> tasksList = new ArrayList<testTask>();
        for (int i = 0; i < 24*7; i++) {
            tasksList.add(new testTask("CELL"+i, "red"));
        }

        // TEST recyclerview with position/data match
        //NEW TESTDATA
        int[] days = {0, 1, 2, 3, 4, 5, 6};
        int[] hours = { 1, 2, 3, 4, 5, 6, 7, 8 , 9 ,10, 11, 12,13,14,15,16,17,18,19,20,21,22,23};

        int[][] priceArray = new int[7][24];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                priceArray[i][j] = 1;
            }
            for (int k = 12; k <24; k++){
                priceArray[i][k] = 3;
            }
        }
        priceArray[0][0] = 3;
        priceArray[0][1] = 2;
        priceArray[0][3] = 2;
        priceArray[6][0] = 2;
        priceArray[6][23] = 1;
        // hour data
        List<String> timeList = new ArrayList<>();

        for (int i = 0; i < 23; i++) {
            String tempString = i + ".00";
            timeList.add(tempString);
        }

        RecyclerView recyclerView = findViewById(R.id.hourView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,7, RecyclerView.VERTICAL,false);
        // scroll to make specified position visible (will scroll the minimal "distance"/lines required)
        // https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager#scrollToPosition(int)
        layoutManager.scrollToPosition(21);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(new MyAdapter(getApplicationContext(), tasksList));
        recyclerView.setAdapter(new TestPriceAdapter(getApplicationContext(), priceArray));
        //recyclerView.setAdapter(new TestPriceAdapterCombined(getApplicationContext(), priceArray,timeList));

        //add lines using recyclerview itemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));

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
    }
}

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
    } **/
