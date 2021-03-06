package com.employeeapi.test_cases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002GetSingleEmployeeRecord extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("***** Started TC002GetSingleEmployeeRecord *****");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        //httpRequest.queryParam("employee_age", "40");

        response = httpRequest.request(Method.GET, "/employee/" + empID);

        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody() {
        logger.info("***** Checking Response Body *****");

        String responseBody = response.getBody().asString();
        logger.info("Response Body ==>" + responseBody);
        Assert.assertTrue(responseBody.contains(empID));
    }

    @Test
    void checkStatusCode() {

        logger.info("***** Checking Status Code *****");

        int statusCode = response.getStatusCode();
        logger.info("Status Code is: ==>" + statusCode);

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkResponseTime() {
        logger.info("***** Checking Response Time *****");

        long responseTime = response.getTime();
        logger.info("Response Time is ==>" + responseTime);

        if (responseTime > 2000) {
            logger.warn("Response time is greater than 2000");
        }

        Assert.assertTrue(responseTime < 10000);
    }

    @Test
    void checkStatusLine() {
        logger.info("***** Checking Status Line *****");

        String statusLine = response.getStatusLine();
        logger.info("Status Line is ==>" + statusLine);
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType() {
        logger.info("***** Checking Content Type *****");

        String contentType = response.header("Content-Type");
        logger.info("Content type is ==>" + contentType);
        Assert.assertEquals(contentType, "application/json;charset=utf-8");
    }

    @Test
    void checkServerType() {
        logger.info("***** Checking Server Type *****");

        String serverType = response.header("Server");
        logger.info("Server type is ==>" + serverType);
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    void checkContentEncoding() {
        logger.info("***** Checking Content Encoding *****");

        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content-Encoding is ==>" + contentEncoding);
        Assert.assertEquals(contentEncoding, "gzip");
    }

    @Test
    void checkContentLength() {
        logger.info("***** Checking Content Length *****");

        String contentLength = response.header("Content-Length");
        logger.info("Content Length is ==>" + contentLength);

        if (Integer.parseInt(contentLength) < 100) {
            logger.warn("Content Length is less than 100");
        }

        Assert.assertTrue(Integer.parseInt(contentLength) < 1500);
    }

    @Test
    void checkCookies() {
        logger.info("***** Checking Cookies *****");

        String cookie = response.getCookie("PHPSESSID");
    }

    @AfterClass
    void tearDown() {
        logger.info("***** Finished TC001GetAllEmployees *****");
    }
}
