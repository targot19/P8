package com.example.javacalenderproject;

import static com.example.javacalenderproject.EnvAccess.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

/*
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        System.out.println("Hello world!");
    }

    @Test
    public void testEnvAccess() {
        Dotenv dotenv = Dotenv.configure() // load dotenv and setup the correct path to env file
                .directory("src/main/assets")
                .filename("env")
                .load();
        String testValue = dotenv.get("ENV_TEST"); // access env variable
        System.out.println("Value from .env file: " + testValue);
    }

    // Generate the Bearer token for authentication
    @Test
    public void testAPIConnection() throws IOException {
        String API_KEY = EnvAccess.API_KEY;
        String API_SECRET = EnvAccess.API_SECRET;

        //System.out.println(ApiAuthenticator.generateBearerToken());
        String bearerToken = ApiAuthenticator.generateBearerToken();
        assertNotNull(bearerToken);

        String url = "https://api.minstroem.app/thirdParty/prices/DK1/forecast";

        // Try to fetch data from the URL
        String data = ApiClient.fetchData(url);
        assertNotNull(data);
        System.out.println(ApiClient.fetchData(url));
    }
}