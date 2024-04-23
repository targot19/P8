package com.example.javacalenderproject.rodJeppeToby;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameView, durationView;
    LinearLayout container;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        durationView = itemView.findViewById(R.id.duration);
        container = itemView.findViewById(R.id.test_view_container);
    }
}
