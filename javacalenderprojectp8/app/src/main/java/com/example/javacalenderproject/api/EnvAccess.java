package com.example.javacalenderproject.api;
import io.github.cdimascio.dotenv.Dotenv;

public class EnvAccess {
    // Class and method to gain access to our env files + variables

    // Instantiate dotenv reader + configure where it should find the env variables:
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("/assets")
            .filename("env")
            .load();

    // Accessing the env variables + storing them in local variables:
    public static final String API_KEY = dotenv.get("API_KEY");
    public static final String API_SECRET = dotenv.get("API_SECRET");
}
