package com.example.javacalenderproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.example.javacalenderproject.databinding.FragmentFirstBinding;
import com.example.javacalenderproject.uilayer.CustomGridAdapter;

public class FamilyGrid extends MainActivity {
    // Make list of images from drawables to use in gridview
    private final Integer[] images = {R.drawable.mom, R.drawable.dad};

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.familymember);

        gridView.setAdapter(new CustomGridAdapter(getApplicationContext(), images));

        return view;

        //binding = FragmentFirstBinding.inflate(inflater, container, false);
        //return binding.getRoot();

    }
}