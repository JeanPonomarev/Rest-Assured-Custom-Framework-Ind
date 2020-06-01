package simple_tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006GetRequestVerifyValuesOfEachNodeInJSON {

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

        JsonPath jsonPath = response.jsonPath();

        System.out.println("---------------------");

        System.out.println(jsonPath.getString("City"));
        System.out.println(jsonPath.getString("Temperature"));
        System.out.println(jsonPath.getString("Humidity"));
        System.out.println(jsonPath.getString("WeatherDescription"));
        System.out.println(jsonPath.getString("WindSpeed"));
        System.out.println(jsonPath.getString("WindDirectionDegree"));

        Assert.assertEquals(jsonPath.getString("Temperature"),"30.79 Degree celsius");
    }
}
