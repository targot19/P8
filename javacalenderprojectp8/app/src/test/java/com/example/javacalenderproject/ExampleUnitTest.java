package com.example.javacalenderproject;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

/*
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        System.out.println("Hello world!");
    }

    /*@Test
    public void envTest() {
        Dotenv dotenv = Dotenv.configure() // load dotenv and setup the correct path to env file
                .directory("/assets")
                .filename("env")
                .load();
        String testValue = dotenv.get("ENV_TEST"); // access env variable
        System.out.println("Value from .env file: " + testValue);
    }*/

    // Generate the Bearer token for authentication
    @Test
    public void testAPIConnection() throws IOException {
        System.out.println(ApiAuthenticator.generateBearerToken(apiKey, apiSecret));

        String url = "https://api.minstroem.app/thirdParty/prices/DK1/forecast";

        // Try to fetch data from the URL
        System.out.println(ApiClient.fetchData(url, "416230B0-9FB1-4D0C-B7A7-22EE2CD3E642", "06b6f35f2b0aef33e58853cf9167254f72bbb8844923c0131e1e311eacab103b"));
    }

    //@Test
    //public void testApiParsing() throws IOException {
        //x
    //}
}
