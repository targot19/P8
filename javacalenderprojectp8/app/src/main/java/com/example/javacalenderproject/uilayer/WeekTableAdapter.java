package com.example.javacalenderproject.uilayer;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;
import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.database.TaskPlanned;
import com.example.javacalenderproject.database.TaskTemplate;
import com.example.javacalenderproject.functionlayer.CreateTaskPlanned;
import com.example.javacalenderproject.functionlayer.CreateWeek;
import com.example.javacalenderproject.functionlayer.SelectedTaskTemplate;
import com.example.javacalenderproject.model.TimeSlot;
import com.example.javacalenderproject.model.Week;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

public class WeekTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // final variables for view types
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    Context context;
    TimeSlot[][] timeSlots;
    List<String> rowHeader;
    LocalDateTime apiFetchTime;

    // constructor
    public WeekTableAdapter(Context context, Week weekData, List<String> timeintervals, LocalDateTime apiFetchTime) {
        this.rowHeader = timeintervals;
        this.context = context;
        timeSlots = weekData.getTimeSlots();
        this.apiFetchTime = apiFetchTime;
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
        else {
            View view = inflater.inflate(R.layout.time_slot_view, parent, false);
            return new TimeSlotViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        if (viewType == TYPE_ITEM) {
            // calculate position ignoring column 0 showing time intervals
            int itemPosition = position - (position/8) -1;
            // calculate day and hour of view from position
            int day = itemPosition % 7;
            int hour = itemPosition / 7;
            // get timeslot from calculated day and hour of the week
            TimeSlot timeSlot = timeSlots[day][hour];
            String priceColor = timeSlot.getColor();
            ArrayList<TaskPlanned> tasks = timeSlot.getTasks();

            // set background color
            if(priceColor == "noColor") {
                ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.white);
            }
            else {
                // apiDateTime test
                //LocalDateTime apiTest = LocalDateTime.of(2024, 5, 1, 13, 0, 1);

                LocalDateTime newPricesDate = LocalDateTime.now().with(LocalTime.of(13,0));
                LocalDate todayDate = LocalDateTime.now().toLocalDate();
                LocalDate timeSlotDate = timeSlot.getDate().toLocalDate();
                LocalDate tomorrowDate = LocalDateTime.now().toLocalDate().plusDays(1);

                // if date of timeslot is today: show stronger colors for certain prices
                if (todayDate.equals(timeSlot.getDate().toLocalDate())) {
                    Log.d("TIMELOGIC", "LocalDateTime.now():" + todayDate);
                    Log.d("TIMELOGIC", "LocalDateTime timeslot:" + timeSlot.getDate().toLocalDate());
                    if (priceColor == "green") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.green);
                    }
                    /** else if (priceColor == "greenyellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.greenyellow);
                    } **/
                    else if (priceColor == "yellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.yellow);
                    }
                    /** else if (priceColor == "orange") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.orange);
                    } **/
                    else if (priceColor == "red") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.red);
                    }
                }
                // else if date of timeslot is tomorrow and time of last successful API price fetch is after 13.00 today: show strong colors for certain prices
                else if (timeSlotDate.equals(tomorrowDate) && apiFetchTime.isAfter(newPricesDate)) {
                    if (priceColor == "green") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.green);
                    }
                    /** else if (priceColor == "greenyellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.greenyellow);
                    } **/
                    else if (priceColor == "yellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.yellow);
                    }
                    /** else if (priceColor == "orange") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.orange);
                    } **/
                    else if (priceColor == "red") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.red);
                    }
                }
                // show less strong colors for uncertain prices
                else {
                    if (priceColor == "green") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.greenForecast);
                    }
                    /** else if (priceColor == "greenyellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.greenyellowForecast);
                    } **/
                    else if (priceColor == "yellow") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.yellowForecast);
                    }
                    /** else if (priceColor == "orange") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.orangeForecast);
                    } **/
                    else if (priceColor == "red") {
                        ((TimeSlotViewHolder)holder).container.setBackgroundResource(R.color.redForecast);
                    }
                }
            }

            // set tasks
            if (!tasks.isEmpty()) {
                // make edge line visible
                ((TimeSlotViewHolder)holder).edge.setVisibility(View.VISIBLE);

                // if 1 task in timeslot: Make 1 task textview visible and set text to name of task
                if(tasks.size() == 1) {
                    ((TimeSlotViewHolder)holder).task1.setVisibility(View.VISIBLE);
                    ((TimeSlotViewHolder)holder).task2.setVisibility(View.GONE);
                    ((TimeSlotViewHolder)holder).separator.setVisibility(View.GONE);
                    String taskName = tasks.get(0).getTaskName();
                    ((TimeSlotViewHolder)holder).task1.setText(taskName);

                }
                else if (tasks.size() > 1) {
                    ((TimeSlotViewHolder)holder).task1.setVisibility(View.VISIBLE);
                    ((TimeSlotViewHolder)holder).task2.setVisibility(View.VISIBLE);
                    ((TimeSlotViewHolder)holder).separator.setVisibility(View.VISIBLE);
                    String text1 = tasks.get(0).getTaskName();
                    String text2 = tasks.get(1).getTaskName();
                    ((TimeSlotViewHolder)holder).task1.setText(text1);
                    ((TimeSlotViewHolder)holder).task2.setText(text2);
                }

            }
            // hide views for tasks if no tasks in timeslot
            else {
                ((TimeSlotViewHolder)holder).edge.setVisibility(View.GONE);
                ((TimeSlotViewHolder)holder).separator.setVisibility(View.GONE);
                ((TimeSlotViewHolder)holder).task1.setVisibility(View.GONE);
                ((TimeSlotViewHolder)holder).task2.setVisibility(View.GONE);
            }

            // onClickListener for planning and creating new task
            ((TimeSlotViewHolder) holder).container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // if more than 1 task in timeslot: return and create no more (add toast for user)
                    if (timeSlot.getTasks().size()<2) {
                        // deactivate on click listener while it's code is running
                        v.setEnabled(false);

                        // get selected taskTemplate from singleton instance
                        SelectedTaskTemplate selectedTaskTemplate = SelectedTaskTemplate.getInstance();
                        TaskTemplate selectedTask = selectedTaskTemplate.getSelectedTaskTemplate();

                        // if taskTemplate is selected
                        if (selectedTask.getTaskName() != null) {
                            // get day and hour from position
                            int position = holder.getAdapterPosition();
                            int timePosition = position - (position/8) -1;

                            LocalDateTime dato = timeSlot.getDate();

                            CreateTaskPlanned.createTask(selectedTask.getTaskName(), dato);
                            timeSlot.addTask(new TaskPlanned(selectedTask.getTaskName(), dato));
                            //selectedTaskTemplate.reset();
                            holder.getBindingAdapter().notifyItemChanged(position);

                        }
                        // reactivate onclicklistener
                        v.setEnabled(true);
                    }

                }
            });

            ((TimeSlotViewHolder) holder).task1.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        TaskPlanned task = timeSlot.getTasks().get(0);
                        new Thread(() -> {
                            TaskDatabase.getDatabase(context).taskPlannedDAO().delete(task.getId());
                            timeSlot.deleteTask(0);
                        }).start();
                        holder.getBindingAdapter().notifyItemChanged(position);
                    }

                    return false;
                }
            });

            ((TimeSlotViewHolder) holder).task2.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        TaskPlanned task = timeSlot.getTasks().get(1);
                        new Thread(() -> {
                            TaskDatabase.getDatabase(context).taskPlannedDAO().delete(task.getId());
                            timeSlot.deleteTask(1);
                        }).start();
                        holder.getBindingAdapter().notifyItemChanged(position);
                    }

                    return false;
                }
            });

            ((TimeSlotViewHolder) holder).task1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // if more than 1 task in timeslot: return and create no more (add toast for user)
                    if (timeSlot.getTasks().size()<2) {
                        // deactivate on click listener while code is running
                        v.setEnabled(false);

                        // get selected taskTemplate from singleton instance
                        SelectedTaskTemplate selectedTaskTemplate = SelectedTaskTemplate.getInstance();
                        TaskTemplate selectedTask = selectedTaskTemplate.getSelectedTaskTemplate();

                        // if taskTemplate is selected
                        if (selectedTask.getTaskName() != null) {
                            // get day and hour from position
                            int position = holder.getAdapterPosition();
                            int timePosition = position - (position/8) -1;

                            LocalDateTime dato = timeSlot.getDate();

                            CreateTaskPlanned.createTask(selectedTask.getTaskName(), dato);
                            timeSlot.addTask(new TaskPlanned(selectedTask.getTaskName(), dato));
                            //selectedTaskTemplate.reset();
                            holder.getBindingAdapter().notifyItemChanged(position);

                        }
                        // reactivate onclicklistener
                        v.setEnabled(true);
                    }
                }
            });

            ((TimeSlotViewHolder) holder).task2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // if more than 1 task in timeslot: return and create no more (add toast for user)
                    if (timeSlot.getTasks().size()<2) {
                        // deactivate on click listener while code is running
                        v.setEnabled(false);

                        // get selected taskTemplate from singleton instance
                        SelectedTaskTemplate selectedTaskTemplate = SelectedTaskTemplate.getInstance();
                        TaskTemplate selectedTask = selectedTaskTemplate.getSelectedTaskTemplate();

                        // if taskTemplate is selected
                        if (selectedTask.getTaskName() != null) {
                            // get day and hour from position
                            int position = holder.getAdapterPosition();
                            int timePosition = position - (position/8) -1;

                            LocalDateTime dato = timeSlot.getDate();

                            CreateTaskPlanned.createTask(selectedTask.getTaskName(), dato);
                            timeSlot.addTask(new TaskPlanned(selectedTask.getTaskName(), dato));
                            //selectedTaskTemplate.reset();
                            holder.getBindingAdapter().notifyItemChanged(position);

                        }
                        // reactivate onclicklistener
                        v.setEnabled(true);
                    }
                }
            });

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

    // method to determine if view is a timeslot in the table or row header showing time in first column
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


