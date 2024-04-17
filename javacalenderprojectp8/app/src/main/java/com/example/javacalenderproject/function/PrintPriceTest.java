package com.example.javacalenderproject.function;

import com.example.javacalenderproject.api.ApiClient;
import com.example.javacalenderproject.model.Price;

import java.io.IOException;
import java.util.ArrayList;

public class PrintPriceTest {
    public static void print() {
        // Get all prices from the API
        try {
            // Call fetchData and store response in an array:
            Price[] allPrices = ApiClient.fetchData();

            // Specify the date for which you want to print prices
            String chosenDate = "2024-04-18"; // Change this to the desired date

            // Filter prices for the desired date
            ArrayList<Price> dailyPrices = Price.getDailyPrices(chosenDate, allPrices);

            // Print prices for the desired date
            System.out.println("Prices for " + chosenDate + ":");
            for (Price price : dailyPrices) {
                System.out.println("Hourly Price: " + price.getHourlyPrice() + ", Date: " + price.getHourlyDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
