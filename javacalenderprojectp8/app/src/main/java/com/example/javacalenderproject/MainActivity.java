package com.example.javacalenderproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import com.example.javacalenderproject.functionlayer.HelpPopup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.javacalenderproject.model.TaskTemplate;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    // static variables for the weeknumber to be dispalyed, weekDisplayed and HourlyPrices
    static int weekOfYear;
    static Week weekDisplayed;
    static HourlyPrice[] allHourlyPrices;

    public static TaskDatabase database;
    // variable to hold time of last successful fetch of price data
    static LocalDateTime apiFetchTime;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);
        // Set database
        database = TaskDatabase.getDatabase(getApplicationContext());

        // CREATE TESTDATA
        //  create testTasks in database
        // TestData.createTestData();
        //  create test price data array
        //HourlyPrice[] testPriceData = TestData.getTestPriceData();
        // clear all data in database
        // database.clearAllTables();

        // Fetch from API for onCreate:
        //HourlyPrice[] allHourlyPrices = new HourlyPrice[0];
        allHourlyPrices = new HourlyPrice[0];
        apiFetchTime = LocalDateTime.MIN; // In case there's no internet, the adapter still needs this to be initialised.
        Log.d("Init LocalDateTime value", apiFetchTime.toString());
        Future<HourlyPrice[]> future = FetchManager.fetchApiData();

        try {
            allHourlyPrices = future.get();
            // Log how many hourly prices were received
            Log.d("ApiOnCreate", "Received " + allHourlyPrices.length + " hourly prices");

            // Log each individual price
            for (HourlyPrice hourlyPrice : allHourlyPrices) {
                Log.d("ApiClient", "Hourly Price: " + hourlyPrice.getPrice() + ", Full Date: " + hourlyPrice.getDate());
                Log.d("Api", "Hour: " + hourlyPrice.getHour());
            }

                // update time of last successful fetch
                apiFetchTime = LocalDateTime.now();

                // Show status for apiFetchTime in UI:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // convert to string
                String apiFetchTimeString = apiFetchTime.format(formatter);
                TextView apiUpdateStatus = findViewById(R.id.updateStatus);
                apiUpdateStatus.setText("Opdateret:" + apiFetchTimeString); // Show value in UI
            //}

        } catch (InterruptedException | ExecutionException e) {
            // Handle exception
            Log.d("ApiClient", "Error fetching data: " + e.getMessage());
            TextView apiUpdateStatus = findViewById(R.id.updateStatus);
            apiUpdateStatus.setText("Opdaterering mislykkedes"); // Don't think this part works
        }


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
        Button btnPreviousWeek = findViewById(R.id.btn_weekminus);
        Button btnNextWeek = findViewById(R.id.btn_weekplus);
        TextView apiUpdateStatus = findViewById(R.id.updateStatus);

        // keep dateViews in list (used to pass to method sto set calendar views)
        ArrayList<TextView> dateViews = new ArrayList<>();
        dateViews.add(weekDay1);
        dateViews.add(weekDay2);
        dateViews.add(weekDay3);
        dateViews.add(weekDay4);
        dateViews.add(weekDay5);
        dateViews.add(weekDay6);
        dateViews.add(weekDay7);

        // 1. Get the current week of the year
        // get today's date
        LocalDate date = LocalDate.now();
        // create weekfields object, specifying that first day of the week is monday and first week of year must have at least 4 days of the year (ISO standard)
        WeekFields weekFields = WeekFields.ISO;
        // get the week of the year using weekFields
        weekOfYear = date.get(weekFields.weekOfYear());

        // 4. get list of dates for today's week
        int year = date.getYear();
        List<LocalDate> weekDates = CreateWeek.getWeekDates(weekOfYear, year);

        // 5. get planned tasks and price data for week:
        List<TaskPlanned> weekTasks = CreateWeek.getWeekTasks(weekDates);
        List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, allHourlyPrices);

        // set dates, week, month for week
        CreateWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);

        // create week object and assign to static variable weekDisplayed
        weekDisplayed = new Week();

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
        WeekTableAdapter weekAdapter = new WeekTableAdapter(getApplicationContext(), weekDisplayed, timeList, apiFetchTime);
        // WeekTableAdapter
        recyclerView.setAdapter(weekAdapter);

        // Create FamilyGrid instance and add the grid to the layout
        FamilyGrid familyGrid = new FamilyGrid();
        familyGrid.createGridView(this);

        // Create TaskTemplate to display tasks in the sidebar
        TaskTemplate taskTemplate = new TaskTemplate();
        taskTemplate.createTaskTemplate(this);


        // Our 'Get Help' text button initializer and onclicklistener
        Button helpButton = findViewById(R.id.button1);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpPopup.showHelpPopup(MainActivity.this);
            }
        });

        // Our 'Get Help' text button initializer and onclicklistener
        ImageButton helpImageButton = findViewById(R.id.help);
        helpImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpPopup.showHelpPopup(MainActivity.this);
            }
        });

        // onclick listeners to next/previous week buttons
        btnPreviousWeek.setOnClickListener(new View.OnClickListener() {
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
                List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, allHourlyPrices);

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

        btnNextWeek.setOnClickListener(new View.OnClickListener() {
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
                List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, allHourlyPrices);

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

        //UPDATE BUTTON:
        // 1.  Access buttons by id:
        Button updateTextButton = findViewById(R.id.updateTextButton);
        ImageButton updateImgButton = findViewById(R.id.updateImgButton);
        // 2. Define reusable onClick listener: UpdateButtonClickListener
        View.OnClickListener updateButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // New fetch:
                Future<HourlyPrice[]> future = FetchManager.fetchApiData();
                Log.d("Update", "Update button was clicked");
                try {
                    // Update contents of allHourlyPrices
                    allHourlyPrices = future.get();
                    // Log how many hourly prices were received
                    Log.d("ApiUpdate", "Received " + allHourlyPrices.length + " hourly prices");

                    // update fetch/update status + display in UI:
                    apiFetchTime = LocalDateTime.now(); // update value
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // reformat to string
                    String apiFetchTimeString = apiFetchTime.format(formatter);
                    apiUpdateStatus.setText("Opdateret:" + apiFetchTimeString); // show updated value in UI
                    apiUpdateStatus.setTextColor(ContextCompat.getColor(v.getContext(), R.color.ForestGreen)); // make sure color is green

                    // Recreate the week w. the new data (this is exactly the same as next/prev week buttons)
                    // 1. get list of dates for week
                    List<LocalDate> weekDates = CreateWeek.getWeekDates(weekOfYear, year);
                    // 2. set dates, week, month for week
                    CreateWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);
                    // 3. get tasks and prices for week
                    List<TaskPlanned> weekTasks = CreateWeek.getWeekTasks(weekDates);
                    List<HourlyPrice> weekPrices = CreateWeek.getWeekPrices(weekDates, allHourlyPrices);
                    // 4. clear data (tasks and pricecolors) of Week object
                    weekDisplayed.clearWeek();
                    // 5. load tasks (and PRICES) into week
                    CreateWeek.loadWeekTasks(weekTasks, weekDisplayed);
                    CreateWeek.loadWeekPrices(weekPrices, weekDisplayed);

                    Log.d("ApiUpdate", "Number of prices: " + weekPrices.size());
                    Log.d("ApiUpdate", "Number of tasks: " + weekTasks.size());

                    // 6. notify adapter that data has changed
                    weekAdapter.notifyDataSetChanged();


                } catch (InterruptedException | ExecutionException e) {
                    // Handle exception
                    Log.d("ApiUpdate", "Error fetching data: " + e.getMessage());
                    apiUpdateStatus.setText("Opdaterering mislykkedes"); // Don't think this part works
                    apiUpdateStatus.setTextColor(ContextCompat.getColor(v.getContext(), R.color.red));
                }
            }
        };
        // 3. Set the onClickListener for both buttons:
        updateTextButton.setOnClickListener(updateButtonClickListener);
        updateImgButton.setOnClickListener(updateButtonClickListener);

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
