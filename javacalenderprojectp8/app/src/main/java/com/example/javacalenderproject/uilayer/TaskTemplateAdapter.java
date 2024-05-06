package com.example.javacalenderproject.uilayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.R;
import com.example.javacalenderproject.database.TaskDatabase;
import com.example.javacalenderproject.database.TaskTemplate;
import com.example.javacalenderproject.functionlayer.SelectedTaskTemplate;

import java.util.List;

public class TaskTemplateAdapter extends RecyclerView.Adapter<TaskTemplateAdapter.ViewHolder> {

    private static List<TaskTemplate> taskTemplates;
    private Context context;

    private Boolean isAnyTaskSelected = false;

    public TaskTemplateAdapter(Context context, List<TaskTemplate> tasks) {
        this.context = context;
        this.taskTemplates = tasks;
    }

    public TaskTemplateAdapter(List<TaskTemplate> tasks) {
        this.taskTemplates = tasks;
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
        TaskTemplate task = taskTemplates.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskTemplates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        // Declare views here
        private final TextView taskTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize views here
            taskTextView = itemView.findViewById(R.id.taskTextView);
            itemView.setOnLongClickListener(this);
            // onclicklistener for selecting tasktemplate
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the clicked item
            int position = getAdapterPosition();
            // Check if the position is valid
            if (position != RecyclerView.NO_POSITION) {
                // Get the task at the clicked position
                TaskTemplate task = taskTemplates.get(position);

                // Create a singleton instance of SelectedTaskTemplate
                SelectedTaskTemplate selectedTask = SelectedTaskTemplate.getInstance();
                // Set the selected task in the singleton
                selectedTask.setSelectedTask(task);
            }

            // Check if any task is selected
            if (isAnyTaskSelected == false){
                // If no task is selected, change the background color of the view and set it as selected
                v.setBackgroundResource(R.color.timeBlock);
                v.setSelected(true);
                // Update the flag to indicate a task is selected
                isAnyTaskSelected = true;
            }
            else if (v.isSelected()) {
                // If the view is already selected, reset its background color and deselect it
                v.setBackgroundResource(R.color.white);
                v.setSelected(false);
                // Update the flag to indicate no task is selected
                isAnyTaskSelected = false;
                // Reset the selected task in the singleton
                SelectedTaskTemplate selectedTask = SelectedTaskTemplate.getInstance();
                selectedTask.reset();
            }
        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            // Pass the long click event to the system's default handler
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
        }

        public boolean onLongClick(View v) {
            // Get the position of the long clicked item
            int position = getAdapterPosition();
            // Check if the position is valid
            if (position != RecyclerView.NO_POSITION) {
                // Get the task at the long clicked position
                TaskTemplate task = taskTemplates.get(position);
                // If the view is already selected, reset its background color, deselect it and reset the selected task in the singleton
                if (v.isSelected()) {
                    isAnyTaskSelected = false;
                    v.setSelected(false);
                    v.setBackgroundResource(R.color.white);
                    SelectedTaskTemplate selectedTask = SelectedTaskTemplate.getInstance();
                    selectedTask.reset();
                }
                // Delete the task
                deleteTask(task);
                // Notify the adapter that the item has been removed
                notifyItemRemoved(position);
            }
            return true;
        }
        public void bind(TaskTemplate task) {
            //Bind data to views here
            taskTextView.setText(task.getTaskName());
        }
        private void deleteTask(TaskTemplate task) {
            new Thread(() -> {
                TaskDatabase.getDatabase(context).taskDAO().delete(task.getId());
                taskTemplates.remove(task);
            }).start();
        }
    }
}
