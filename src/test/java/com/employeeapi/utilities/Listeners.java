package com.employeeapi.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext testContext) {

        // specify location
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/myReport.html");

        // Title of a report
        htmlReporter.config().setDocumentTitle("Automation Report");
        // Name of a report
        htmlReporter.config().setReportName("Rest API Testing Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Project Name", "Employee Database API");
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "jean_ponomarev");
    }

    public void onTestSuccess(ITestResult result) {

        // create new entry in the report
        test = extent.createTest(result.getName());

        test.log(Status.PASS, "Test Case PASSED IS" + result.getName());
    }

    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getName());

        //to add name in the extent report
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());

        // to add error/exception in the extent report
        test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getName());

        test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

}
