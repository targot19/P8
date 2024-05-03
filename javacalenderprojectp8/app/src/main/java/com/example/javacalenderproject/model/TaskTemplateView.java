package com.example.javacalenderproject.model;

import static com.example.javacalenderproject.MainActivity.database;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.MainActivity;
import com.example.javacalenderproject.R;
import com.example.javacalenderproject.database.TaskTemplate;
import com.example.javacalenderproject.uilayer.TaskTemplateAdapter;

import java.util.List;

public class TaskTemplateView {

    List<TaskTemplate> tasktmpl = database.taskDAO().getAllTasks();

    public void createTaskTemplate(MainActivity activity) {

        // Find the RecyclerView with ID "taskview" and set its LayoutManager
        RecyclerView recyclerView = activity.findViewById(R.id.taskview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        // Find the EditText with ID "tilføj_opgave" for user input
        EditText editText = activity.findViewById(R.id.tilfoej_opgave);

        // Find the Button with ID "addtask" for adding tasks
        Button addTask = activity.findViewById(R.id.addtask);

        // Create a new TaskTemplateAdapter instance and set it as the adapter for the RecyclerView
        TaskTemplateAdapter taskTemplateAdapter = new TaskTemplateAdapter(tasktmpl);
        recyclerView.setAdapter(taskTemplateAdapter);

        // Add initial tasks to the list


      /*  // Set an OnEditorActionListener for the EditText to handle the "Done" action from the soft keyboard
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String taskName = editText.getText().toString().trim();
                if (!taskName.isEmpty()) {
                    tasks.add(taskName);
                    taskTemplateAdapter.notifyItemInserted(tasks.size() - 1);
                    editText.setText("");
                } else {
                    Toast.makeText(activity, "Indtast opgave", Toast.LENGTH_SHORT).show();
                }
                // Hide the soft keyboard
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                return true;
            }
            return false;
        }); */

        /**
        // Hide soft keyboard, when adding task
        editText = findViewById(R.id.tilfoej_opgave);
        addtask = findViewById(R.id.addtask);

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        }); **/

        // Set an OnClickListener for the "Add Task" button
        addTask.setOnClickListener(v -> {
            String taskName = editText.getText().toString().trim();
            if (!taskName.isEmpty()) {
                // Add the task to the list, update the RecyclerView, and clear the EditText
                TaskTemplate newtasktemplate = new TaskTemplate(taskName);
                tasktmpl.add(newtasktemplate);
                database.taskDAO().insertTask(newtasktemplate);
                taskTemplateAdapter.notifyItemInserted(tasktmpl.size() - 1);
                editText.setText("");
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            } else {
                // Display a Toast message if the EditText is empty
                Toast.makeText(activity, "Indtast opgave", Toast.LENGTH_SHORT).show();
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
    }
}









/*
    private void createTask(String name, String icon, String color, int duration) {
        Task newTask = new Task(name, icon, color, duration);
        new Thread(() -> {
            try {
                database.taskDAO().insertTask(newTask);
                List<Task> tasks = database.taskDAO().getAllTasks();
                Log.d("MainActivity", "Task inserted, total tasks now: " + tasks.size());
                for (Task task : tasks) {
                    Log.d("MainActivity", "Task: " + task.getTaskName());
                }
            } catch (Exception e) {
                Log.e("MainActivity", "Failed to insert or retrieve tasks", e);
            }
        }).start();
    }
}

 */