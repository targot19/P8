package com.example.javacalenderproject.api;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

// Class for making API requests using okhttp
public class ApiClient {

    // HTTP client for sending request
    private static final OkHttpClient client = new OkHttpClient();

    // Method to fetch data from website URL using API key and secret for authentication
    public static void fetchData(final Callback callback) {
        // Generate the authorization token
        String authorizationToken = ApiAuthenticator.generateBearerToken();

        // Create a new API request with the Authorization header as specified in the documentation
        Request request = new Request.Builder()
                .url("https://api.minstroem.app/thirdParty/prices/DK1/forecast")
                .header("Authorization", authorizationToken)
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = null;
                try {
                    if (response.isSuccessful()) {
                        responseData = response.body().string();
                        Gson gson = new Gson();
                        PriceData priceData = gson.fromJson(responseData, PriceData.class);
                        callback.onSuccess(priceData);
                    } else {
                        callback.onError(new IOException("Unexpected response code: " + response.code()));
                    }
                } catch (IOException e) {
                    if (responseData != null) {
                        callback.onError(new IOException("Error parsing JSON: " + e.getMessage()));
                    } else {
                        callback.onError(e);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onError(e);
            }
        });
    }

    // Callback interface to handle success and error responses
    public interface Callback {
        void onSuccess(PriceData priceData);
        void onError(Exception e);
    }
}


    /* OLD CODE, NON-ASYNC
    // Method to fetch data from website URL using API key and secret for authentication
    public static String fetchData() throws IOException {
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
                return response.body().string(); // it's here we want to edit to parse response properly
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (Exception e) {
            // Log errors if any
            e.printStackTrace();
            return e.getMessage();
        }
    }
}*/


/* Old line 31-35
    Then send the request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();  it's here we want to parse response properly */