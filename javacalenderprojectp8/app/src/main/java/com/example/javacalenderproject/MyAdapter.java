package com.example.javacalenderproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<testTask> testTasks;

    public MyAdapter(Context context, List<testTask> testTasks) {
        this.context = context;
        this.testTasks = testTasks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.test_view,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameView.setText(testTasks.get(position).getName());
        holder.durationView.setText(String.valueOf(testTasks.get(position).getDuration()));
        //set colors of view from data
        if (testTasks.get(position).getColor() == "green") {
            holder.container.setBackgroundResource(R.color.green);
        }
        else if (testTasks.get(position).getColor() == "yellow") {
            holder.container.setBackgroundResource(R.color.yelllow);
        }
        else {
            holder.container.setBackgroundResource(R.color.red);
        }
    }

    @Override
    public int getItemCount() {
        return testTasks.size();
    }
}
