package com.example.javacalenderproject.api;

import android.util.Log;
import com.example.javacalenderproject.model.HourlyPrice;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FetchManager {
    // Fetches API data asynchronously, into array of HourlyPrice on success
    public static Future<HourlyPrice[]> fetchApiData() {
        // Creates a single-threaded ExecutorService for managing background tasks
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Creates a CompletableFuture object that will hold the result of the asynchronous operation
        CompletableFuture<HourlyPrice[]> future = new CompletableFuture<>();

        ApiClient.fetchData(new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(HourlyPrice[] allHourlyPrices) {
                Log.d("ApiClient", "Received " + allHourlyPrices.length + " hourly prices");
                future.complete(allHourlyPrices); // Completes the CompletableFuture with the received hourly prices
            }

            @Override
            public void onFailure(IOException e) {
                Log.d("ApiClient", "Unsuccessful");
                future.completeExceptionally(e);
            }
        });

        executor.shutdown(); // Shuts down the ExecutorService after submitting the task
        return future; // Returns the CompletableFuture object (fetchApiData result)
    }
}