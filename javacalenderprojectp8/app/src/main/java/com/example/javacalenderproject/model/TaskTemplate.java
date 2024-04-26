package com.example.javacalenderproject.model;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.javacalenderproject.MainActivity;
import com.example.javacalenderproject.R;
import com.example.javacalenderproject.uilayer.TaskTemplateAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskTemplate {

    List<String> tasks = new ArrayList<>(); // Initialize the tasks list

    public void createTaskTemplate(MainActivity activity) {

        // Set taskview in UI to be recyclerview
        RecyclerView recyclerView = activity.findViewById(R.id.taskview);

        EditText editText = activity.findViewById(R.id.tilføj_opgave);

        Button addTask = activity.findViewById(R.id.addtask);

        TaskTemplateAdapter taskTemplateAdapter = new TaskTemplateAdapter(tasks);
        recyclerView.setAdapter(taskTemplateAdapter);

        // Add tasks initially
        tasks.add("Vask tøj");
        tasks.add("Tænd opvaskemaskinen");

        // v -> is lambda
        // addTask.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v)
        addTask.setOnClickListener(v -> {
            if (TextUtils.isEmpty(editText.getText())) {
                Toast.makeText(activity, "Indtast opgave", Toast.LENGTH_SHORT).show();
            } else {
                tasks.add(editText.getText().toString().trim());
                taskTemplateAdapter.notifyItemInserted(tasks.size() - 1);
                editText.setText("");
            }
        });
    }
}
