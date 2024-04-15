/**package com.example.javacalenderproject;


 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>


public class ExampleUnitTest {
    // An example Hello World test to ensure the test runs correctly
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        System.out.println("Hello world!");
    }

    // Test for the env vars, this pulls a ENV_TEST variable
    // (make one if you want to pass the test, can contain anything)
    @Test
    public void testEnvAccess() {
        Dotenv dotenv = Dotenv.configure() // load dotenv and setup the correct path to env file
                .directory("src/main/assets")
                .filename("env")
                .load();
        String testValue = dotenv.get("ENV_TEST"); // access env variable
        System.out.println("Value from .env file: " + testValue);
    }

    // Generate Bearer token for authentication and test fetching
    @Test
    public void testAPIConnection() throws IOException {
        // Try to generate the right Bearer token
        String bearerToken = ApiAuthenticator.generateBearerToken();
        assertNotNull(bearerToken); // Ensure token is not empty
        System.out.println(ApiAuthenticator.generateBearerToken()); // Print the token

        // Try to fetch data from the URL
        String data = ApiClient.fetchData();
        assertNotNull(data); // Ensure there is data fetched
        System.out.println(ApiClient.fetchData()); // Print the fetched data
    }
} **/
