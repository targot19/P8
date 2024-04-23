package com.example.javacalenderproject.uilayer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.model.TimeSlot;
import com.example.javacalenderproject.model.Week;

import java.util.ArrayList;
import java.util.List;

public class WeekTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // define view types
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    Context context;
    TimeSlot[][] timeSlots;
    List<String> rowHeader;

    // constructor
    public WeekTableAdapter(Context context, Week weekData, List<String> timeintervals) {
        this.rowHeader = timeintervals;
        this.context = context;
        timeSlots = weekData.getTimeSlots();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create layout inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        // return view holder based on view type
        if (viewType == TYPE_HEADER) {
            View view = inflater.inflate(R.layout.time_view, parent, false);
            return new TimeViewHolder(view);
        }
        /*else if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.test_price_view, parent, false);
            return new TestPriceViewHolder(view);
        }
        // In case ViewType does not match return null. throw exception instead?
        return null; */
        else {
            View view = inflater.inflate(R.layout.time_slot_view, parent, false);
            return new TimeSlotViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        // calculate position ignoring column 0 showing time intervals
        int itemPosition = position - (position/8) -1;
        // calculate day and hour from position
        int day = itemPosition % 7 ;
        int hour = itemPosition / 7;

        if (viewType == TYPE_ITEM) {
            // get price using day and hour from timeslo
            String priceColor = timeSlots[day][hour].getColor();
            ArrayList<TaskPlanned> tasks = timeSlots[day][hour].getTasks();
            // set background color
            if (priceColor == "green") {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.green);
            }
            else if (priceColor == "yellow") {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.yellow);
            }
            else if (priceColor == "red") {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.red);
            }
            // force to set background to white if no color/data to show
            else {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.white);
            }

            // set tasks: REVIDERES: skal læse og vise flere tasks!
            if (!tasks.isEmpty()) {
                ((TimeSlotViewHolder)holder).task1.setVisibility(View.VISIBLE);
                String text = tasks.get(0).getTaskName();
                ((TimeSlotViewHolder)holder).task1.setBackgroundResource(R.color.task);
                ((TimeSlotViewHolder)holder).task1.setText(text);
            }
            // force to hide textview if no tasks in timeslot
            else if (tasks.isEmpty()) {
                ((TimeSlotViewHolder)holder).task1.setVisibility(View.GONE);
            }

            //-------- FOR TESTING: toast + logging
            // show toast message on long click
            ((TimeSlotViewHolder) holder).container.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), "LONG test - Adap.pos: " + holder.getAdapterPosition() + "pos " + position, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            //LOGGING
            Log.d("Adapter", "Color retrieved for position " + position + ": " + priceColor);

        } else if (viewType == TYPE_HEADER) {
            // calculate index in rowHeader list from position
            int index = position / 8;
            // set text to timeinterval
            ((TimeViewHolder)holder).time.setText(rowHeader.get(index));
            // set background color
            ((TimeViewHolder)holder).container.setBackgroundResource(R.color.timeBlock);

            // show toast message on long click
            ((TimeViewHolder) holder).container.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), "LONG test - Adap.pos: " + holder.getAdapterPosition() + "pos " + position, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    // method to determine view type
    @Override
    public int getItemViewType(int position) {
        // every 8th position is in column 0 -> is row header to show time intervals
        if (position % 8 == 0) return TYPE_HEADER;
        // all other positions should show timeslots
        else return TYPE_ITEM;
    }
    @Override
    public int getItemCount() {
        // calculate and return number of items
        return timeSlots.length * timeSlots[0].length + rowHeader.size();
    }

}


