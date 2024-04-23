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
            View view = inflater.inflate(R.layout.time_view, parent, false);
            return new TimeViewHolder(view);
        }
        else if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.time_slot_view, parent, false);
            return new TimeSlotViewHolder(view);
        }
        // throw exception instead?
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_ITEM) {
            // calculate day and hour from position
            int timePosition = position - (position/8) -1;
            int day = timePosition % 7 ;
            int hour = timePosition / 7;
            // get price using day and hour from 2D array
            int price = prices[day][hour];
            // set colors from price
            if (price == 1) {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.green);
            } else if (price == 2) {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.yellow);
            } else if (price == 3) {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.red);
            }
        } else if (viewType == TYPE_HEADER) {
            int index = position / 8;
            ((TimeViewHolder)holder).time.setText(timeintervals.get(index));
            // TEST TIME CLASSES
            //String timeZoneId = "Europe/Copenhagen"; // Replace with your desired timezone ID
            //ZoneId zoneId = ZoneId.of(timeZoneId);
            //((TimeViewHolder)holder).time.setText(""+ZonedDateTime.now(zoneId).getHour());
            //((TimeViewHolder)holder).time.setText(LocalDateTime.now().toString());
            ((TimeViewHolder)holder).container.setBackgroundResource(R.color.timeBlock);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 8 == 0) return TYPE_HEADER;
        else return TYPE_ITEM;
    }
    @Override
    public int getItemCount() {
        // calculate and return number of items in array
        return prices.length * prices[0].length + timeintervals.size();
    }
}

