package com.example.javacalenderproject;

import javax.crypto.Mac;
        import javax.crypto.spec.SecretKeySpec;
        import java.nio.charset.StandardCharsets;

//import android.nfc.Tag;
import android.util.Base64;
//import android.util.Log;

public class ApiAuthenticator {

    public static void main(String[] args) {
        // API retrieved from Min Strøm
        String apiKey = "416230B0-9FB1-4D0C-B7A7-22EE2CD3E642";
        String apiSecret = "06b6f35f2b0aef33e58853cf9167254f72bbb8844923c0131e1e311eacab103b";

        try {
            // Create HMAC-SHA256 hash
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hashedBytes = sha256_HMAC.doFinal(apiKey.getBytes(StandardCharsets.UTF_8));

            // Concatenate API key with hashed value
            String concatenated = apiKey + ":" + bytesToHex(hashedBytes);

            // Base64 encode the concatenation
            String base64Encoded = Base64.encodeToString(concatenated.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP);

            // Include in HTTP header
            String bearerToken = "Bearer " + base64Encoded;
            System.out.println("Authorization: " + bearerToken);
        } catch (Exception e) {
            //Log.e(Tag, "An error occurred", e);
            e.printStackTrace();
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
