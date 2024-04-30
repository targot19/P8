package com.example.javacalenderproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.javacalenderproject.api.FetchManager;
import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.functionlayer.CreateTaskPlanned;
import com.example.javacalenderproject.functionlayer.CreateWeek;
import com.example.javacalenderproject.functionlayer.SetupHourView;
import com.example.javacalenderproject.functionlayer.TestData;
import com.example.javacalenderproject.model.HourlyPrice;
import com.example.javacalenderproject.model.TimeSlot;
import com.example.javacalenderproject.model.Week;
import com.example.javacalenderproject.uilayer.WeekTableAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    // Declare the MaterialCalendarView and Calendar objects.
    MaterialCalendarView calendarView;
    java.util.Calendar calendar;

    public static TaskDatabase database;

    // static variables for the weeknumber to be dispalyed and weekDisplayed
    static int weekOfYear;
    static Week weekDisplayed;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);
        database = TaskDatabase.getDatabase(getApplicationContext());

        // TEST: API call & fetch data + do something with it.
        //FetchManager.fetchApiData();

        // Initialize the calendarView by finding it in the layout.
        //calendarView = findViewById(R.id.calenderView);

        // Initialize tableLayout by finding it from tablelayout.xml.
        //TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Get an instance of the current calendar.
        //calendar = Calendar.getInstance();

        // Set an initial date for the calendar view.
        //setDate(1, 1, 2024);
/*
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

 */
        // CREATE TESTDATA
        //  create testTasks in database
        //TestData.createTestData();
        //  create test price data array
        HourlyPrice[] testPriceData = TestData.getTestPriceData();
        // clear all data in database
        //database.clearAllTables();

        // get views by id: dateviews, weekview, monthview, recyclerview:
        TextView weekView = findViewById(R.id.week_tv);
        TextView monthView = findViewById(R.id.month_tv);
        RecyclerView recyclerView = findViewById(R.id.hourView);
        TextView weekDay1 = findViewById(R.id.date0_tv);
        TextView weekDay2 = findViewById(R.id.date1_tv);
        TextView weekDay3 = findViewById(R.id.date2_tv);
        TextView weekDay4 = findViewById(R.id.date3_tv);
        TextView weekDay5 = findViewById(R.id.date4_tv);
        TextView weekDay6 = findViewById(R.id.date5_tv);
        TextView weekDay7 = findViewById(R.id.date6_tv);
        // change week buttons
        //View btnPreviousWeek = findViewById(R.id.btn_weekminus);
        //View btnNextWeek = findViewById(R.id.btn_weekplus);
        //btnPreviousWeek.setOnClickListener(this);
        //btnNextWeek.setOnClickListener(this);

        // keep dateViews in list (used to pass to methods)
        ArrayList<TextView> dateViews = new ArrayList<>();
        dateViews.add(weekDay1);
        dateViews.add(weekDay2);
        dateViews.add(weekDay3);
        dateViews.add(weekDay4);
        dateViews.add(weekDay5);
        dateViews.add(weekDay6);
        dateViews.add(weekDay7);


        // 1. get today's date /  set date (todays date on program  start)
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2024,5,10);
        // week 13 test
        //LocalDate date = LocalDate.of(2024,3,25);

        // 2. Get the week of the year from date
        // create weekfields object, specifying that first day of the week is monday and first week of year must have at least 4 days of the year (ISO standard)
        WeekFields weekFields = WeekFields.ISO;
        // get the week of the year using weekFields
        weekOfYear = date.get(weekFields.weekOfYear());

        // 3. get year from date (required to get dates of the week)
        int year = date.getYear();

        // 4. get list of dates for given weeknumber of the given year
        List<LocalDate> weekDates = CreateWeek.getWeekDates(weekOfYear, year);

        // 5. get planned tasks and price data for week:
        List<TaskPlanned> weekTasks = CreateWeek.getWeekTasks(weekDates);
        List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, testPriceData);

        // set dates, week, month for week
        CreateWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);

        // create week object and assign to global variable weekDisplayed
        weekDisplayed = new Week();


        // Trying out 'future' stuff for API - future.get is our async call
        HourlyPrice[] allHourlyPrices = new HourlyPrice[0];
        Future<HourlyPrice[]> future = FetchManager.fetchApiData();
        try {
            allHourlyPrices = future.get();
            // Log how many hourly prices were received
            Log.d("ApiClient", "Received " + allHourlyPrices.length + " hourly prices");

            // Log each individual price
            for (HourlyPrice hourlyPrice : allHourlyPrices) {
                Log.d("ApiClient", "Hourly Price: " + hourlyPrice.getPrice() + ", Full Date: " + hourlyPrice.getDate());
                Log.d("Api",  "Hour: " + hourlyPrice.getHour());
            }

        } catch (InterruptedException | ExecutionException e) {
            // Handle exception
            Log.d("ApiClient", "Error fetching data: " + e.getMessage());
        }

        /*
        // test: add tasks from database to week
        if (weekTasks.size()>4) {
            testWeek.getTimeSlots()[1][9].addTask(weekTasks.get(0));
            testWeek.getTimeSlots()[2][9].addTask(weekTasks.get(1));
            testWeek.getTimeSlots()[3][9].addTask(weekTasks.get(2));
            testWeek.getTimeSlots()[4][9].addTask(weekTasks.get(3));
            testWeek.getTimeSlots()[5][9].addTask(weekTasks.get(4));
        }
         */
        // TEST get length of weekTasks of weekPrices and show as task
        TaskPlanned taskLen = new TaskPlanned("Length WeekTasks: " +weekTasks.size());
        weekDisplayed.getTimeSlots()[0][9].addTask(taskLen);
        TaskPlanned pricesLen = new TaskPlanned("Length weekPrices: " +weekPrices.size());
        weekDisplayed.getTimeSlots()[0][10].addTask(pricesLen);

        // load weeks planned tasks and weeks prices into weekDisplayed
        CreateWeek.loadWeekTasks(weekTasks, weekDisplayed);
        CreateWeek.loadWeekPrices(weekPrices, weekDisplayed);

        // pass all necessary arguments to SetupHourView to setup recyclerview showing the week data in the UI
        // SetupHourView måske en ringe ide (gør ikke koden lettere læselig). Måske bedre at have koden i MainActivity..
        //SetupHourView.setup(this, recyclerView, getApplicationContext(), testWeek);

        GridLayoutManager layoutManager = new GridLayoutManager(this,8, RecyclerView.VERTICAL,false);
        // scroll to make specified position visible initially (will scroll the minimal "distance"/lines required) https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager#scrollToPosition(int)
        layoutManager.scrollToPosition(60);
        recyclerView.setLayoutManager(layoutManager);
        //add lines between cells with recyclerview itemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        // get list of timeIntervals
        List<String> timeList= CreateWeek.getTimeIntervals();
        // create adapter
        WeekTableAdapter weekAdapter = new WeekTableAdapter(getApplicationContext(), weekDisplayed, timeList);
         // WeekTableAdapter
        recyclerView.setAdapter(weekAdapter);

        //  get button Views by id with other Views in start of onCreate() method ?
        // onclick listeners to next/previous week buttons

        findViewById(R.id.btn_weekminus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (weekOfYear>1) {weekOfYear = weekOfYear -1;}

                // 1. get list of dates for specified weeknumber and year
                List<LocalDate> weekDates = CreateWeek.getWeekDates(weekOfYear, year);

                // 2. set dates, week, month for week
                CreateWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);

                // 3. get tasks and prices for week
                List<TaskPlanned> weekTasks = CreateWeek.getWeekTasks(weekDates);
                // OBS: weekprices testdata
                List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, TestData.getTestPriceData());

                // 4. clear data (tasks and pricecolors) of Week object
                weekDisplayed.clearWeek();

                // 5. load tasks (and PRICES) into week
                // TEST get length of weekTasks of weekPrices and show as task
                TaskPlanned taskLen = new TaskPlanned("Length WeekTasks: " +weekTasks.size());
                weekDisplayed.getTimeSlots()[0][9].addTask(taskLen);
                TaskPlanned pricesLen = new TaskPlanned("Length weekPrices: " +weekPrices.size());
                weekDisplayed.getTimeSlots()[0][10].addTask(pricesLen);

                // load data (not test)
                CreateWeek.loadWeekTasks(weekTasks, weekDisplayed);
                CreateWeek.loadWeekPrices(weekPrices, weekDisplayed);

                // 6. notify adapter that data has changed
                weekAdapter.notifyDataSetChanged();

            }
        });


        findViewById(R.id.btn_weekplus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekOfYear<53) {weekOfYear = weekOfYear +1;}

                // 1. get list of dates for week
                List<LocalDate> weekDates = CreateWeek.getWeekDates(weekOfYear, year);

                // 2. set dates, week, month for week
                CreateWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);

                // 3. get tasks and prices for week
                List<TaskPlanned> weekTasks = CreateWeek.getWeekTasks(weekDates);
                // OBS: weekprices testdata
                List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, TestData.getTestPriceData());

                // 4. clear data (tasks and pricecolors) of Week object
                weekDisplayed.clearWeek();

                // 5. load tasks (and PRICES) into week
                // TEST get length of weekTasks of weekPrices and show as task
                TaskPlanned taskLen = new TaskPlanned("Lenght WeekTasks: " +weekTasks.size());
                weekDisplayed.getTimeSlots()[0][9].addTask(taskLen);
                TaskPlanned pricesLen = new TaskPlanned("Length weekPrices: " +weekPrices.size());
                weekDisplayed.getTimeSlots()[0][10].addTask(pricesLen);

                CreateWeek.loadWeekTasks(weekTasks, weekDisplayed);
                CreateWeek.loadWeekPrices(weekPrices, weekDisplayed);

                // 6. notify adapter that data has changed
                weekAdapter.notifyDataSetChanged();

            }
        });



    }


}
/**
 * // calender ting
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

    public void setDate(int day, int month, int year) {
        // Set the calendar to the specified year, month, and day.
        calendar.set(Calendar.YEAR, year);
        // Subtract 1 from month because Calendar.MONTH is 0-based.
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

 } **/
