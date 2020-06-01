package simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002PostRequest {

    @Test
    void registrationSuccessful() {

        // Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object

        // Request payload sending along with post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("FirstName", "John123123");
        requestParams.put("LastName", "Doe12312");
        requestParams.put("UserName", "JohnUser123123");
        requestParams.put("Password", "01011995");
        requestParams.put("Email", "j132123ohn@gmail.com");

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString()); // attach above data to the request

        // Response object
        Response response = httpRequest.request(Method.POST, "/register");

        // Print response in console
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " + responseBody);

        // status code validation
        int statusCode = response.getStatusCode();

        System.out.println("Status code is: " + statusCode);

        Assert.assertEquals(statusCode, 201);

        // success code validation
        String successCode = response.jsonPath().get("SuccessCode");

        Assert.assertEquals(successCode, "OPERATION_SUCCESS");
    }

}
