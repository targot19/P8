package com.example.javacalenderproject.uilayer;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.javacalenderproject.R;
import com.example.javacalenderproject.functionlayer.WeekNumberHelper;
import com.example.javacalenderproject.functionlayer.WeekNumberTest;

public class WeekNumberDisplay {
    // Everything for showing weeknumber and related buttons:
    TextView weekNumberTextView;
    Button weekNumberIncrement;
    Button weekNumberDecrement;

    public void test() {
        //TEST: Week Number
        WeekNumberTest weekNumberTest = new WeekNumberTest();
        weekNumberTest.runTest();
    }
    public void show(Activity activity) {
        // Connecting logic from WeekNumberHelper to week number display + buttons:
        WeekNumberHelper weekNumberHelper = new WeekNumberHelper(); // instantiate WeekNumberHelper to access methods for getting/changing week number
        weekNumberTextView = activity.findViewById(R.id.weekNumberView);
        weekNumberTextView.setText("Week: " + weekNumberHelper.getDisplayedWeekNumber());
        weekNumberIncrement = activity.findViewById(R.id.weekNumberIncrement);
        weekNumberDecrement = activity.findViewById(R.id.weekNumberDecrement);
        // Set OnClickListener for increment/decrement buttons
        weekNumberIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the incrementWeekNumber() method
                weekNumberHelper.incrementWeekNumber();

                // Update TextView with the new week number
                weekNumberTextView.setText("Week: " + weekNumberHelper.getDisplayedWeekNumber());
            }
        });
        weekNumberDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the incrementWeekNumber() method
                weekNumberHelper.decrementWeekNumber();
                // Update TextView with the new week number
                weekNumberTextView.setText("Week: " + weekNumberHelper.getDisplayedWeekNumber());
            }
        });
    }
}
