package com.example.javacalenderproject.model;

import java.util.ArrayList;

// Each item from the json will be an object of the Price class, which contains date + price for an hour:
public class HourlyPrice {
    private double price;
    private String date;

    // Constructor
    public HourlyPrice(double price, String date) {
        this.price = price;
        this.date= date;
    }


    // Methods:
    public double getHourlyPrice() { // In case we want to access only the price
        return price;
    } // returns specific price

    public String getHourlyDate() {
        return date;
    } // returns specific date


}
