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
                    String jsonResponse = response.body().string();
                    Log.d("ApiClient", "Response Body: " + jsonResponse);
                    Gson gson = new Gson();
                    HourlyPrice[] allHourlyPrices = gson.fromJson(jsonResponse, HourlyPrice[].class);
                    callback.onSuccess(allHourlyPrices);
                } else {
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

/*
    // Method to fetch data from website URL using API key and secret for authentication
    public static Price[] fetchData() throws IOException {
        // Generate the authorization token
        String authorizationToken = ApiAuthenticator.generateBearerToken();

        // Create a new API request with the Authorization header as specified in the documentation
        Request request = new Request.Builder()
                .url("https://api.minstroem.app/thirdParty/prices/DK1/forecast")
                .header("Authorization", authorizationToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Store the response in a variable:
                String jsonResponse = response.body().string();

                // return jsonResponse; // (for testing)

                // Return json parsed into an array of objects:
                Gson gson = new Gson();
                Price[] allPrices = gson.fromJson(jsonResponse, Price[].class);
                return allPrices;


            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (Exception e) {
            // Log errors if any
            e.printStackTrace();
            // return e.getMessage(); // (for testing)
            return new Price[0]; // returns an empty array
        }
    }
     */


/* Old line 31-35
    Then send the request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();  it's here we want to parse response properly */