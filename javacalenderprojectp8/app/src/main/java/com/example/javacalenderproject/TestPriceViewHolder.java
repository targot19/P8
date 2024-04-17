package com.example.javacalenderproject;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestPriceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    LinearLayout container;
    public TestPriceViewHolder(@NonNull View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.test_price_view_container);

        itemView.setOnClickListener(this);
    }
    // onclick method
    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        int day = position % 7;
        int hour = position/7;
        // toast meassage showing day and hour of clicked view item
        Toast.makeText(v.getContext(), "Day: " + day + "  hour " + hour, Toast.LENGTH_SHORT).show();
    }

}
