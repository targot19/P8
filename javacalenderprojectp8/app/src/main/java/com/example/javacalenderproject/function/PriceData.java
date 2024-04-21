package com.example.javacalenderproject.function;
import com.google.gson.annotations.SerializedName;

public class PriceData {
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
}
