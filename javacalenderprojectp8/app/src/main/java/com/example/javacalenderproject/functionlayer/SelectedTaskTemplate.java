package com.example.javacalenderproject.functionlayer;
import com.example.javacalenderproject.uilayer.TaskTemplate;

public class SelectedTaskTemplate {
    private static SelectedTaskTemplate instance;
    private TaskTemplate selectedTask;

    private SelectedTaskTemplate() {
        selectedTask = new TaskTemplate();
    }

    public static SelectedTaskTemplate getInstance() {
        if (instance == null) {
            instance = new SelectedTaskTemplate();
        }
        return instance;
    }

    public TaskTemplate getSelectedTaskTemplate() {
        return selectedTask;
    }

    public void setSelectedTask(TaskTemplate selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void reset() {this.selectedTask = new TaskTemplate();}
}
