package com.employeeapi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "22"; // hard-coded - input for Get details of Single Employee & update employee

    public Logger logger;

    @BeforeClass
    public void setup() {
        logger = Logger.getLogger("EmployeeRestAPI"); // added Logger
        PropertyConfigurator.configure("log4j.properties"); // added logger
        logger.setLevel(Level.DEBUG);
    }
}
