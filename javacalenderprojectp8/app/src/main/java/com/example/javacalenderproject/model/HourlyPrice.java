package com.example.javacalenderproject.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// Each item from the json will be an object of the Price class, which contains date + price for an hour:
public class HourlyPrice {
    private double price;
    private Date date;

    // Constructor
    public HourlyPrice(double price, Date date) {
        this.price = price;
        this.date = date; // date as string
    }


    // Methods: get & set
    public double getPrice() { // In case we want to access only the price
        return price;
    } // returns specific price

    public Date getDate() {
        return date;
    }

    public LocalDateTime getConvertedDate() {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public int getHour() {
        return date.getHours();
    }

    public int getHourFromConvertedDate() {
        return getConvertedDate().getHour();
    }


    // Helper methods:


}
