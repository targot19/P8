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
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                TaskTemplate task = taskTemplates.get(position);

                // SEND THE DATA - HOW?

                // create singleton instance
                SelectedTaskTemplate selectedTask = SelectedTaskTemplate.getInstance();
                selectedTask.setSelectedTask(task);
            }

            // SOME FUNCTION TO ALLOW THE USER TO SELECT/DESELECT TaskTemplate
            // Vi har brug for at ingen taskTeamplates er selected når brugeren har oprettet en task

            // forsøg på noget select/unselect - men det er noget værre rod
            if (v.isSelected() == false){
                v.setBackgroundResource(R.color.timeBlock);
                v.setSelected(true);
            }
            else {
                v.setBackgroundResource(R.color.white);
                v.setSelected(false);
                SelectedTaskTemplate selectedTask = SelectedTaskTemplate.getInstance();
                selectedTask.reset();
            }

        }

        @Override
        public boolean onLongClickUseDefaultHapticFeedback(@NonNull View v) {
            return View.OnLongClickListener.super.onLongClickUseDefaultHapticFeedback(v);
        }
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                TaskTemplate task = taskTemplates.get(position);
                deleteTask(task);
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
                TaskDatabase.getDatabase(context).taskDAO().delete(task.getTaskName());
                taskTemplates.remove(task);
            }).start();
        }
    }
}
