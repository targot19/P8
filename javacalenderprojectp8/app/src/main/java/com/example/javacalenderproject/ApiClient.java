package com.example.javacalenderproject;

import java.io.IOException;
import java.net.URI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Class for making API requests using okhttp
public class ApiClient {

    // HTTP client for sending requests
    private static final OkHttpClient client = new OkHttpClient();

    // Method to fetch data from website URL using API key and secret for authentication
    public static String fetchData(String url, String apiKey, String apiSecret) throws IOException {
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
                return response.body().string(); // it's here we want to parse response properly
            } else {
                throw new IOException("Unexpected response code: " + response.code());
            }
        } catch (Exception e) {
            // Log errors if any
            e.printStackTrace();
            return e.getMessage();
        }
    }
}



/* Old line 31-35
    Then send the request and retrieve the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();  it's here we want to parse response properly */