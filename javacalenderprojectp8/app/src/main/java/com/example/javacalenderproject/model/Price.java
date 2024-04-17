package com.example.javacalenderproject.model;

import java.util.ArrayList;

// Each item from the json will be an object of the Price class, which contains date + price for an hour:
public class Price {
    private double price;
    private String date;

    // Constructor
    public Price (double price, String date) {
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


    // Method that takes a date + array of all prices, and filters the list.
    // Returns an ArrayList of hourly prices for that specific date:
    public static ArrayList<Price> getDailyPrices(String date, Price[] allPrices) {
        if (date == null || allPrices == null) {
            System.out.println("Date and allPrices must have an acceptable value");
        }

        ArrayList<Price> dailyPrices = new ArrayList<>();
        // populate the ArrayList
        for (Price price : allPrices) {
            if (price.getHourlyDate().equals(date)) {
                dailyPrices.add(price); // adds price object to ArrayList (containing both date and price inst. var)
            }
        }
        return dailyPrices;
    }

    // Price[day][hour]
    // Method that returns all prices, organized in a 2 dimensional array.
    public static void getPriceForecastTable() {

    }

}
