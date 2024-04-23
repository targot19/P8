package com.example.javacalenderproject.uilayer;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;

public class TimeViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout container;
    public TextView time;

    public TimeViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.time_container);
        time = itemView.findViewById(R.id.time_textview);
    }
}

