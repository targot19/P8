package com.example.javacalenderproject.functionlayer;

import android.content.Context;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.model.Week;
import com.example.javacalenderproject.uilayer.WeekTableAdapter;

import java.util.ArrayList;
import java.util.List;

public class SetupHourView {
    public static void setup(Context context, RecyclerView recyclerView, Context applicationContext, Week weekData) {

        /*
        // create time data for row header -- move to adapter?
        List<String> timeList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String tempString = i + ".00";
            if (i <10) { tempString = "0" + tempString;}
            timeList.add(tempString);
        } */

        List<String> timeList= CreateWeek.getTimeIntervals();

        // initialize gridlayoutmanager. Spancount = number of columns
        GridLayoutManager layoutManager = new GridLayoutManager(context,8, RecyclerView.VERTICAL,false);

        // scroll to make specified position visible initially (will scroll the minimal "distance"/lines required) https://developer.android.com/reference/androidx/recyclerview/widget/LinearLayoutManager#scrollToPosition(int)
        layoutManager.scrollToPosition(60);
        // set layout manager of recyclerview
        recyclerView.setLayoutManager(layoutManager);
        //add lines using recyclerview itemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.HORIZONTAL));
        // set adapter and pass data
        recyclerView.setAdapter(new WeekTableAdapter(applicationContext, weekData, timeList));
    }
}
