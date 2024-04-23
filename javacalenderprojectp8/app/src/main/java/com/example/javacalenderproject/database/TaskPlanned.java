package com.example.javacalenderproject.database;
import com.example.javacalenderproject.FamilyMember;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskPlanned {
    int id;
    String name;
    LocalDateTime date;
    int hour;
    int duration;
    ArrayList<FamilyMember> assignee;

    TaskPlanned(String name, LocalDateTime date) {
        this.name = name;
        this.date = date;
        // get hour from date to set hour
        duration = 0;
    }
    public TaskPlanned(String name) {
        this.name = name;
        this.date = null;
        // get hour from date to set hour
        duration = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<FamilyMember> getAssignee() {
        return assignee;
    }

    public void setAssignee(ArrayList<FamilyMember> assignee) {
        this.assignee = assignee;
    }
}
