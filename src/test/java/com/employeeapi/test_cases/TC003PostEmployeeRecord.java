package com.employeeapi.test_cases;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003PostEmployeeRecord extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSal();
    String empAge = RestUtils.empAge();

    @BeforeClass
    void createEmployee() throws InterruptedException {

        logger.info("***** Started TC003PostEmployeeRecord *****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        // JSONObject is a class that represents a simple JSON we can add Key-Value pairs using the put method
        // {"name":"John123","salary":"123","age":"23"}
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        // add a header starting the request body is json
        httpRequest.header("Content-Type", "application/json");

        // add the json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/create");

        Thread.sleep(5000);
    }

    @Test
    void checkResponseBody() {
        String responseBody = response.getBody().asString();

        Assert.assertTrue(responseBody.contains(empName));
        Assert.assertTrue(responseBody.contains(empSalary));
        Assert.assertTrue(responseBody.contains(empAge));
    }

    @Test
    void checkStatusCode() {
        //logger.info("***** Checking Status Code *****");
        int statusCode = response.getStatusCode();
        //logger.info("Status Code is: ==>" + statusCode);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkStatusLine() {
        //logger.info("***** Checking Status Line *****");

        String statusLine = response.getStatusLine();
        //logger.info("Status Line is ==>" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {
        //logger.info("***** Checking Content Type *****");

        String contentType = response.header("Content-Type");
        //logger.info("Content type is ==>" + contentType);
        Assert.assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    void checkServerType() {
        //logger.info("***** Checking Server Type *****");

        String serverType = response.header("Server");
        //logger.info("Server type is ==>" + serverType);
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    void checkContentEncoding() {
        //logger.info("***** Checking Content Encoding *****");

        String contentEncoding = response.header("Content-Encoding");
        //logger.info("Content-Encoding is ==>" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @AfterClass
    void tearDown() {
        logger.info("***** Finished TC001GetAllEmployees *****");
    }
}
