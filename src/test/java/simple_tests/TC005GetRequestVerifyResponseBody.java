package simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005GetRequestVerifyResponseBody {

    @Test
    public void getWeatherDetails() {

        // Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET, "/Delhi");

        // Print response in console
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

        Assert.assertTrue(responseBody.contains("Delhi"));
    }
}
