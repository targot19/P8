package com.example.javacalenderproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.api.FetchManager;
import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.model.TaskPlanned;
import com.example.javacalenderproject.functionlayer.WritePricesToDatabase;
import com.example.javacalenderproject.functionlayer.DisplayWeek;
import com.example.javacalenderproject.uilayer.HelpPopup;
import com.example.javacalenderproject.model.HourlyPrice;
import com.example.javacalenderproject.uilayer.TaskTemplateView;
import com.example.javacalenderproject.model.Week;
import com.example.javacalenderproject.uilayer.FamilyGrid;
import com.example.javacalenderproject.uilayer.WeekTableAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    // static variables for week number, year, and the Week object to be displayed
    static int weekOfYear;
    static int year;
    static Week weekDisplayed;

    // variable to hold time of last successful fetch of price data
    static LocalDateTime apiFetchTime;

    public static TaskDatabase database;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file.
        setContentView(R.layout.activity_main);

        // Set database
        database = TaskDatabase.getDatabase(getApplicationContext());

        // create and initialize variable to hold the fetched price data
        HourlyPrice[] allHourlyPrices = new HourlyPrice[0];

        // create week object and assign to static variable weekDisplayed
        weekDisplayed = new Week();

        // Fetch from API for onCreate:
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

            Toast.makeText(getApplicationContext(), "Kunne ikke hente elpris data. Opdater eller tjek din internet forbindelse", Toast.LENGTH_LONG).show();
        }

        // get views by id: dateviews, weekview, monthview, recyclerview, apiupdataStatus:
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
        TextView apiUpdateStatus = findViewById(R.id.updateStatus);

        // keep dateViews in list (to pass to method to set calendar views)
        ArrayList<TextView> dateViews = new ArrayList<>();
        dateViews.add(weekDay1);
        dateViews.add(weekDay2);
        dateViews.add(weekDay3);
        dateViews.add(weekDay4);
        dateViews.add(weekDay5);
        dateViews.add(weekDay6);
        dateViews.add(weekDay7);

        // update database with new fetched prices (rest of the code is only executed when write is done)
        WritePricesToDatabase.priceToDatabase(allHourlyPrices).thenRun(() -> {
            runOnUiThread(() -> {
                // get dates of the current week of the year
                // get today's date
                LocalDate dateToday = LocalDate.now();
                // create weekfields object, specifying that first day of the week is monday and first week of year must have at least 4 days of the year (ISO standard)
                WeekFields weekFields = WeekFields.ISO;
                // get the week of the year using weekFields
                weekOfYear = dateToday.get(weekFields.weekOfYear());
                // get list of dates for today's week
                year = dateToday.getYear();

                updateWeekData(dateViews, weekView, monthView);

                /*
                List<LocalDate> weekDates = DisplayWeek.getWeekDates(weekOfYear, year);

                // get planned tasks and price data for week:
                List<TaskPlanned> weekTasks = DisplayWeek.getWeekTasks(weekDates);
                List<HourlyPrice> weekPrices = DisplayWeek.getWeekPrices(weekDates);

                // set dates, week, month for week in the calendarview
                DisplayWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);

                // load weeks planned tasks and weeks prices into weekDisplayed
                DisplayWeek.loadWeekTasks(weekTasks, weekDisplayed);
                DisplayWeek.loadWeekPrices(weekPrices, weekDisplayed);
                DisplayWeek.loadWeekDates(weekDates, weekDisplayed);

                 */

                // create and set up layout manager and recyclerview
                GridLayoutManager layoutManager = new GridLayoutManager(this, 8, RecyclerView.VERTICAL, false);
                // scroll to make specified position visible initially (will scroll the minimal "distance"/lines required) https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager#scrollToPosition(int)
                layoutManager.scrollToPosition(60);
                recyclerView.setLayoutManager(layoutManager);
                //add lines between cells with recyclerview itemDecoration
                recyclerView.addItemDecoration(new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,
                        DividerItemDecoration.HORIZONTAL));
                // get list hours from 00.00 to 23.00
                List<String> timeList = DisplayWeek.getTimeIntervals();
                // create week table adapter and set to be the adapter of the recyclerview
                WeekTableAdapter weekAdapter = new WeekTableAdapter(getApplicationContext(), weekDisplayed, timeList, apiFetchTime);
                // WeekTableAdapter
                recyclerView.setAdapter(weekAdapter);

                // Create FamilyGrid instance and add the grid to the layout
                FamilyGrid familyGrid = new FamilyGrid();
                familyGrid.createGridView(this);

                // Create TaskTemplate to display tasks in the sidebar
                TaskTemplateView taskTemplateView = new TaskTemplateView();
                taskTemplateView.createTaskTemplate(this);

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

                // CHANGE WEEK BUTTONS
                // 1. get buttons by id
                Button btnPreviousWeek = findViewById(R.id.btn_weekminus);
                Button btnNextWeek = findViewById(R.id.btn_weekplus);

                // 2. set onclick listeners
                btnPreviousWeek.setOnClickListener(new View.OnClickListener() {
                    // update week of year variable
                    @Override
                    public void onClick(View v) {
                        if (weekOfYear > 1) {
                            weekOfYear = weekOfYear - 1;
                        }

                        // call method to update the data of weekDisplayed and the dates shown in calendarView
                        updateWeekData(dateViews, weekView, monthView);

                        // notify adapter that data has changed
                        weekAdapter.notifyDataSetChanged();
                    }
                });
                btnNextWeek.setOnClickListener(new View.OnClickListener() {
                    // update week of year variable
                    @Override
                    public void onClick(View v) {
                        if (weekOfYear < 53) {
                            weekOfYear = weekOfYear + 1;
                        }

                        // call method to update the data of weekDisplayed and the dates shown in calendarView
                        updateWeekData(dateViews, weekView, monthView);

                        // notify adapter that data has changed
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
                            HourlyPrice[] allHourlyPrices = future.get();
                            // Log how many hourly prices were received
                            Log.d("ApiUpdate", "Received " + allHourlyPrices.length + " hourly prices");

                            // Store fetched data into the database
                            WritePricesToDatabase.priceToDatabase(allHourlyPrices);

                            // update fetch/update status + display in UI:
                            apiFetchTime = LocalDateTime.now(); // update value
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // reformat to string
                            String apiFetchTimeString = apiFetchTime.format(formatter);
                            apiUpdateStatus.setText("Opdateret:" + apiFetchTimeString); // show updated value in UI
                            apiUpdateStatus.setTextColor(ContextCompat.getColor(v.getContext(), R.color.ForestGreen)); // make sure color is green

                            // call to update the data of weekDisplayed and the dates shown in calendarView
                            updateWeekData(dateViews, weekView, monthView);

                            /*
                            Log.d("ApiUpdate", "Number of prices: " + weekPrices.size());
                            Log.d("ApiUpdate", "Number of tasks: " + weekTasks.size());
                             */

                            // notify adapter that data has changed
                            weekAdapter.notifyDataSetChanged();

                        } catch (InterruptedException | ExecutionException e) {
                            // Handle exception
                            Log.d("ApiUpdate", "Error fetching data: " + e.getMessage());
                            apiUpdateStatus.setText("Opdatering mislykkedes"); // Don't think this part works
                            apiUpdateStatus.setTextColor(ContextCompat.getColor(v.getContext(), R.color.red));
                        }
                    }
                };
                // 3. Set the onClickListener for both buttons:
                updateTextButton.setOnClickListener(updateButtonClickListener);
                updateImgButton.setOnClickListener(updateButtonClickListener);
            });
        });
    }

    // method to update the data of weekDisplayed and the dates shown in calendarView
    private void updateWeekData(ArrayList<TextView> dateViews, TextView weekView, TextView monthView) {

        // 1. get list of dates for week
        List<LocalDate> weekDates = DisplayWeek.getWeekDates(weekOfYear, year);

        // 2. get tasks and prices for week
        List<TaskPlanned> weekTasks = DisplayWeek.getWeekTasks(weekDates);
        List<HourlyPrice> weekPrices = DisplayWeek.getWeekPrices(weekDates);

        // 3. clear data (timeslots) of Week object
        weekDisplayed.clearWeek();

        // 4. load tasks, prices and dates into weekDisplayed
        DisplayWeek.loadWeekTasks(weekTasks, weekDisplayed);
        DisplayWeek.loadWeekPrices(weekPrices, weekDisplayed);
        DisplayWeek.loadWeekDates(weekDates, weekDisplayed);

        // 5. set dates, week, and month in in the calendarView in in the UI
        DisplayWeek.setCalendarView(dateViews, weekView, monthView, weekDates, weekOfYear);
    }
}


