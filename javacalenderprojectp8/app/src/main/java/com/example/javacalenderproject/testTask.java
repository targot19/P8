package com.example.javacalenderproject;

import static java.sql.Types.NULL;

public class testTask {
    String color;
    String name;

    testTask(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() { return color;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
