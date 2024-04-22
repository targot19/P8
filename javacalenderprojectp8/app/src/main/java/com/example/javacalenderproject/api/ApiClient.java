package com.example.javacalenderproject.api;

import android.util.Log;

import com.example.javacalenderproject.model.HourlyPrice;
import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Class for making API requests using okhttp
public class ApiClient {

    // HTTP client for sending request
    private static final OkHttpClient client = new OkHttpClient();
    // Define an interface for callback
    public interface ApiCallback {
        void onSuccess(HourlyPrice[] allHourlyPrices);
        void onFailure(IOException e);
    }

    // New version of fetchData:
    public static void fetchData(final ApiCallback callback) {
        String authorizationToken = ApiAuthenticator.generateBearerToken();

        Request request = new Request.Builder()
                .url("https://api.minstroem.app/thirdParty/prices/DK1/forecast")
                .header("Authorization", authorizationToken)
                .build();

        // Messages for testing:
        Log.d("ApiClient", "Request URL: " + request.url());
        Log.d("ApiClient", "Request Headers: " + request.headers());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Store JSON response as one long String
                    String jsonResponse = response.body().string();
                    // Log jsonResponse, for testing:
                    Log.d("ApiClient", "Response Body: " + jsonResponse);

                    // Instantiate Gson (for parsing Json into objects)
                    Gson gson = new Gson();
                    // Parse json into objects of the class HourlyPrice + assign to array
                    HourlyPrice[] allHourlyPrices = gson.fromJson(jsonResponse, HourlyPrice[].class);
                    // "return" the allHourlyPrices array if fetch is successful:
                    callback.onSuccess(allHourlyPrices);
                } else {
                    // "return" an error message, if fetch fails:
                    callback.onFailure(new IOException("Unexpected response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }
        });
    }
}

