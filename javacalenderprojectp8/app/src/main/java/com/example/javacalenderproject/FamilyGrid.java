package com.example.javacalenderproject;

import android.widget.GridView;

import com.example.javacalenderproject.uilayer.CustomGridAdapter;

public class FamilyGrid {
    // Make list of images from drawables to use in gridview
    private final Integer[] images = {R.drawable.mom, R.drawable.dad};

    public void createGridView(MainActivity activity) {
        // Find the grid view in the activity layout
        GridView gridView = activity.findViewById(R.id.familymember);

        // Set the adapter to display the images in the grid view
        gridView.setAdapter(new CustomGridAdapter(activity, images));
    }
}