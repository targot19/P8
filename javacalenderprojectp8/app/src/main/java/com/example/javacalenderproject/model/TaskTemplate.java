package com.example.javacalenderproject.model;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.MainActivity;
import com.example.javacalenderproject.R;
import com.example.javacalenderproject.uilayer.TaskTemplateAdapter;

import java.util.ArrayList;
import java.util.List;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.app.Activity;




public class TaskTemplate {

    List<String> tasks = new ArrayList<>(); // Initialize the tasks list

    public void createTaskTemplate(MainActivity activity) {

        // Find the RecyclerView with ID "taskview" and set its LayoutManager
        RecyclerView recyclerView = activity.findViewById(R.id.taskview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        // Find the EditText with ID "tilføj_opgave" for user input
        EditText editText = activity.findViewById(R.id.tilføj_opgave);

        // Find the Button with ID "addtask" for adding tasks
        Button addTask = activity.findViewById(R.id.addtask);

        // Create a new TaskTemplateAdapter instance and set it as the adapter for the RecyclerView
        TaskTemplateAdapter taskTemplateAdapter = new TaskTemplateAdapter(tasks);
        recyclerView.setAdapter(taskTemplateAdapter);

        // Add initial tasks to the list
        tasks.add("Vask tøj");
        tasks.add("Tænd opvaskemaskinen");

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

        // Set an OnClickListener for the "Add Task" button
        addTask.setOnClickListener(v -> {
            String taskName = editText.getText().toString().trim();
            if (!taskName.isEmpty()) {
                // Add the task to the list, update the RecyclerView, and clear the EditText
                tasks.add(taskName);
                taskTemplateAdapter.notifyItemInserted(tasks.size() - 1);
                editText.setText("");
            } else {
                // Display a Toast message if the EditText is empty
                Toast.makeText(activity, "Indtast opgave", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

