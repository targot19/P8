package com.example.javacalenderproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestPriceAdapterCombined extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    Context context;
    int[][] prices;
    List<String> timeintervals;

    public TestPriceAdapterCombined(Context context, int[][] prices, List<String> timeintervals) {
        this.timeintervals = timeintervals;
        this.context = context;
        this.prices = prices;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.timeblock_view, parent, false);
            return new TimeViewHolder(view);
        }
        else if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.test_price_view, parent, false);
            return new TestPriceViewHolder(view);
        }
        // throw exception instead?
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_ITEM) {
            // calculate day and hour from position
            int day = position % 7;
            int hour = position / 7;
            // get price using day and hour from 2D array
            int price = prices[day][hour];
            // set colors from price
            if (price == 1) {
                ((TestPriceViewHolder)holder).container.setBackgroundResource(R.color.green);
            } else if (price == 2) {
                ((TestPriceViewHolder)holder).container.setBackgroundResource(R.color.yellow);
            } else if (price == 3) {
                ((TestPriceViewHolder)holder).container.setBackgroundResource(R.color.red);
            }
        } else if (viewType == TYPE_HEADER) {
            int index = position / 8;
            ((TimeViewHolder)holder).time.setText(timeintervals.get(index));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 8  == 0) return TYPE_HEADER;
        else return TYPE_ITEM;
    }
    @Override
    public int getItemCount() {
        // calculate and return number of items in array
        return prices.length * prices[0].length + timeintervals.size();
    }
}

