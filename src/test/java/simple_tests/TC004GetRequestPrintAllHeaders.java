package simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TC004GetRequestPrintAllHeaders {

    @Test
    public void getWeatherDetails() {

        // Specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        // Print response in console
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " + responseBody);

        // capture all the headers from response
        Headers allHeaders = response.headers();

        // print header key - value

        System.out.println("Headers:");

        for (Header header : allHeaders) {
            System.out.println(header.getName() + " - " + header.getValue());
        }
    }

}
