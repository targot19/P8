package com.example.javacalenderproject.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// Each item from the json will be an object of the Price class, which contains date + price for an hour:
public class HourlyPrice {
    private double price;
    private Date date;

    // Constructor
    public HourlyPrice(double price, Date date) {
        this.price = price;
        this.date = date; // date as Date (Gson default)
    }


    // Methods: get & set
    public double getPrice() { // In case we want to access only the price
        return price;
    } // returns specific price

    public LocalDateTime getDate() {
        // Converts Date (supported by Gson, but deprecated) object to type LocalDateTime.
            // toInstant converts time to seconds.
            // atZone adjusts value to default timezone of the system.
            // toLocalDateTime converts to LocalTimeDate object
        return date.toInstant().atZone(ZoneId.of("Europe/Copenhagen")).toLocalDateTime();
    }

    public int getHour() {
        return getDate().getHour(); // uses LocalDateTime method to access hour
    }

    // Hvis man skal kalde indbyggede metoder på HourlyPrice: HourlyPrice.getDate().namePåMetode()

}
