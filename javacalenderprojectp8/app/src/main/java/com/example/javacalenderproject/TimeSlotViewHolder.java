package com.example.javacalenderproject;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimeSlotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    LinearLayout container;
    TextView task1;
    public TimeSlotViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.time_slot_view_container);
        task1 = itemView.findViewById(R.id.task1);

        // on click listener for testing position calculation/databind logic
        itemView.setOnClickListener(this);
    }

    // on click listener for test
    @Override
    public void onClick(View v) {
        //int position = getAdapterPosition();
        int position = getBindingAdapterPosition();
        int timePosition = position - (position/8) -1;
        int day = timePosition % 7;
        int hour = timePosition/7;
        // toast message showing day and hour of clicked view item
        Toast.makeText(v.getContext(), "Day: " + day + "  hour " + hour, Toast.LENGTH_SHORT).show();
    }

}
