package com.example.javacalenderproject.api;

import android.util.Log;

import com.example.javacalenderproject.model.HourlyPrice;

import java.io.IOException;

public class FetchManager {
    public static void fetchApiData() {
        ApiClient.fetchData(new ApiClient.ApiCallback() {
            // If fetchData is successful: Update UI with data
            @Override
            public void onSuccess(HourlyPrice[] allHourlyPrices) {
                // Everything that should happen when API Call is successful:

                Log.d("ApiClient", "Received " + allHourlyPrices.length + " hourly prices");
                // Log each individual price
                for (HourlyPrice hourlyPrice : allHourlyPrices) {
                    Log.d("ApiClient", "Hourly Price: " + hourlyPrice.getPrice() + ", Full Date: " + hourlyPrice.getDate());
                    Log.d("Api",  "Hour: " + hourlyPrice.getHour());
                    Log.d("ApiClient", "Hourly Price: " + hourlyPrice.getPrice() + ", LDT: " + hourlyPrice.getConvertedDate());
                    Log.d("Api",  "Hour lDT: " + hourlyPrice.getHourFromConvertedDate());
                }

            }

            // If fetchData fails: Print error message
            @Override
            public void onFailure(IOException e) {
                Log.d("ApiClient", "Unsuccessful");
            }
        });
    }
}
