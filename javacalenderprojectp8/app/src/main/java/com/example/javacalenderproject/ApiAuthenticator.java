package com.example.javacalenderproject;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HexFormat;

// Class for authenticating API requests by generating bearer tokens
public class ApiAuthenticator {

    // Default constructor that initializes a bearer token using predefined API key and secret - doesn't do anything right now
    ApiAuthenticator () {
        String bearerToken = generateBearerToken(this.apiKey, this.apiSecret);
    }

    Dotenv dotenv = Dotenv.configure() // load dotenv and setup the correct path to env file
            .directory("/assets")
            .filename("env")
            .load();

    // Consider if this should be moved to gradle.properties to maintain discretion in this code
    private static String apiKey = dotenv.get("API_KEY");
    private static String apiSecret = dotenv.get("API_SECRET");

    // Method to generate a bearer token for API authentication
    public static String generateBearerToken() {
        try {
            // Create HMAC-SHA256 hash
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            //SecretKeySpec secret_key = new SecretKeySpec(HexFormat.of().parseHex(apiSecret),"HmacSHA256"); - udkommenteret for nu, gammel metode
            sha256_HMAC.init(secret_key);
            byte[] hashedBytes = sha256_HMAC.doFinal(apiKey.getBytes(StandardCharsets.UTF_8));

            // Concatenate API key with hashed value
            String concatenated = apiKey + ":" + bytesToHex(hashedBytes);

            // Base64 encode the concatenation
            String base64Encoded = Base64.getEncoder().encodeToString(concatenated.getBytes(StandardCharsets.UTF_8));

            // Include in HTTP header
            return "Bearer " + base64Encoded;
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // Function to convert byte array to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}