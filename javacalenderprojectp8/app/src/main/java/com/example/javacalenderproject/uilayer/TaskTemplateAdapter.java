package com.example.javacalenderproject.uilayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;

import java.util.List;

public class TaskTemplateAdapter extends RecyclerView.Adapter<TaskTemplateAdapter.ViewHolder> {

    private final List<String> tasks;

    public TaskTemplateAdapter(List<String> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Implement this method to inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Implement this method to bind data to the ViewHolder
        String task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare views here
        private final TextView taskTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize views here
            taskTextView = itemView.findViewById(R.id.taskTextView);
        }

        public void bind(String task) {
            //Bind data to views here
            taskTextView.setText(task);
        }
    }
}
