package com.example.javacalenderproject.function;
import com.example.javacalenderproject.api.ApiClient;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.OkHttpClient;

public class PriceData {
    private final ApiClient apiClient = new ApiClient();

    @SerializedName("price")
    private double price;

    @SerializedName("date")
    private String date;

    // Getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void print() {

        apiClient.fetchData(new ApiClient.Callback() {
            @Override
            public void onSuccess(List<PriceData> responseData) {
                // Print the fetched data
                System.out.println("Fetched Data: " + responseData);
                // You can parse the JSON data here if needed
            }

            @Override
            public void onError(Exception e) {
                // Handle error
                e.printStackTrace();
            }
        });
    }
}
    /*@SerializedName("price")
    private double price;

    @SerializedName("date")
    private String date;

    // Getters and setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

/*
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

 */