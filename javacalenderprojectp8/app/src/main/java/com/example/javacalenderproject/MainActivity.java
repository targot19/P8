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

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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

        //
        // TEST recyclerview with position/data match
        //TESTDATA
        int[][] priceArray = new int[7][24];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                priceArray[i][j] = 1;
            }
            for (int k = 12; k <24; k++){
                priceArray[i][k] = 3;
            }
        }
        priceArray[3][3] = 3;
        priceArray[0][0] = 3;
        priceArray[0][1] = 2;
        priceArray[6][0] = 2;
        priceArray[6][23] = 1;


        // create test week data
        Week testWeek = CreateWeek.createTestWeek();
        // ---- setup recyclerview for weekplan
        // create recyclerview and initialize by finding view by id
        RecyclerView recyclerView = findViewById(R.id.hourView);
        // pass all necessary arguments to SetupHourView to setup recyclerview showing the week data in the UI
        // SetupHourView måske en ringe ide (gør ikke koden lettere læselig). Måske bedre at have koden i MainActivity?
        SetupHourView.setup(this,recyclerView, getApplicationContext(), testWeek);

        // moved to SetupHourView class. Better to keep in MainActivity?
        /*
        GridLayoutManager layoutManager = new GridLayoutManager(this,8, RecyclerView.VERTICAL,false);
        // scroll to make specified position visible initially (will scroll the minimal "distance"/lines required) https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager#scrollToPosition(int)
        layoutManager.scrollToPosition(60);
        recyclerView.setLayoutManager(layoutManager);
        //add lines using recyclerview itemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));

         // WeekTableAdapter
        recyclerView.setAdapter(new WeekTableAdapter(getApplicationContext(), testWeek, timeList));
        */



        // TestPriceAdapterCombined (added time column)
        //recyclerView.setAdapter(new TestPriceAdapterCombined(getApplicationContext(), priceArray,timeList));



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

