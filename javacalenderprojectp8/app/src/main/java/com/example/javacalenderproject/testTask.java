package com.example.javacalenderproject;

public class testTask {
    String color;
    String name;
    int duration;

    testTask(String name, int duration, String color) {
        this.name = name;
        this.duration = duration;
        this.color = color;
    }

    public String getColor() { return color;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
