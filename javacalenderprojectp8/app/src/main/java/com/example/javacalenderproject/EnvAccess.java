package com.example.javacalenderproject;
import io.github.cdimascio.dotenv.Dotenv;

public class EnvAccess {
    /*private static final String ENV_VAR_KEY = "API_KEY";
    private static final String ENV_VAR_SECRET = "API_SECRET";

    public static final String API_KEY;
    public static final String API_SECRET;

    static {
        API_KEY = System.getenv(ENV_VAR_KEY);
        API_SECRET = System.getenv(ENV_VAR_SECRET);
    }*/
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/main/assets")
            .filename("env")
            .load();

    public static final String API_KEY = dotenv.get("API_KEY");
    public static final String API_SECRET = dotenv.get("API_SECRET");
}
