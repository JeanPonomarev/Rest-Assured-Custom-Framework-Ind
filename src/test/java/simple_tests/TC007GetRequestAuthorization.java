package simple_tests;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC007GetRequestAuthorization {

    @Test
    public void testAuthorization() {

        // Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

        // Basic authentication
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");

        RestAssured.authentication = authScheme;

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET, "/");

        // print response in console window
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

        // status code validation
        int statusCode = response.statusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 200);
    }
}
