package simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001GetRequest {

    @Test
    void getWeatherDetails() {

        // Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        // Print response in console
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " + responseBody);

        // status code validation
        int statusCode = response.getStatusCode();

        System.out.println("Status code is: " + statusCode);

        Assert.assertEquals(statusCode, 200);

        // status line verification
        String statusLine = response.getStatusLine();

        System.out.println("Status line is: " + statusLine);

        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

}
