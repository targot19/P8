package com.example.javacalenderproject.functionlayer;

import static com.example.javacalenderproject.MainActivity.database;
import android.util.Log;
import com.example.javacalenderproject.model.TaskPlanned;
import com.example.javacalenderproject.model.HourlyPrice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WritePricesToDatabase {

    public static CompletableFuture<Void> priceToDatabase(HourlyPrice[] priceData) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                for (HourlyPrice hourlyPrice : priceData) {
                    database.HourlyPriceDAO().insertOrUpdate(hourlyPrice);
                }

                HourlyPrice[] prices = database.HourlyPriceDAO().getAllPrices();
                Log.d("MainActivity", "Prices inserted, total prices now: " + prices.length);
                for (HourlyPrice price : prices) {
                    Log.d("MainActivity", "Price: " + price.getPrice());
                }

                future.complete(null); // Complete the future when the database operation is done
            } catch (Exception e) {
                Log.e("MainActivity", "Failed to insert or retrieve prices", e);
                future.completeExceptionally(e); // Complete the future exceptionally if an error occurs
            }
        }).start();

        return future;
    }

}


