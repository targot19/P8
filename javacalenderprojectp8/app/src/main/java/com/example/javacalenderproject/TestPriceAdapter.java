package com.example.javacalenderproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestPriceAdapter extends RecyclerView.Adapter<TestPriceViewHolder> {

    Context context;
    int[][] prices;

    public TestPriceAdapter(Context context, int[][] prices) {
        this.context = context;
        this.prices = prices;
    }
    @NonNull
    @Override
    public TestPriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestPriceViewHolder(LayoutInflater.from(context).inflate(R.layout.test_price_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestPriceViewHolder holder, int position) {
        // calculate day and hour from position
        int day = position % 7;
        int hour = position/7;
        // get price using day and hour from 2D array
        int price = prices[day][hour];
        // set colors from price
        if (price ==1 ){
            holder.container.setBackgroundResource(R.color.green);
        } else if (price == 2) {
            holder.container.setBackgroundResource(R.color.yellow);
        } else if (price == 3) {
            holder.container.setBackgroundResource(R.color.red);
        }
    }

    @Override
    public int getItemCount() {
        // calculate and return number of items in array
        return prices.length * prices[0].length;
    }
}
