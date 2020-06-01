package data_driven_tests;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlUtils;

import java.io.IOException;

public class DataDrivenTestAddNewEmployees {

    @Test(dataProvider = "empDataProvider")
    public void postNewEmployees(String eName, String eSal, String eAge) {

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest = RestAssured.given();

        // here we created data which can be sent along with the post request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", eName);
        requestParams.put("salary", eSal);
        requestParams.put("age", eAge);

        // add a header stating the request body is a JSON
        httpRequest.header("Content-Type", "application/json");

        // add the JSON to the body of the request
        httpRequest.body(requestParams.toJSONString());

        // sending post request
        Response response = httpRequest.request(Method.POST, "/create");

        // capture response body to perform validations
        String responseBody = response.getBody().asString();

        System.out.println("Response body is: " + responseBody);

        Assert.assertTrue(responseBody.contains(eName));
        Assert.assertTrue(responseBody.contains(eSal));
        Assert.assertTrue(responseBody.contains(eAge));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @DataProvider(name = "empDataProvider")
    Object[][] getEmpData() throws IOException {

        // Read data from excel
        String path = System.getProperty("user.dir") + "/src/test/java/data_driven_tests/empdata.xlsx";

        int rowNum = XLUtils.getRowCount(path, "Лист1");
        int colCount = XLUtils.getCellCount(path, "Лист1", 1);

        String empData[][] = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                empData[i - 1][j] = XLUtils.getCellData(path, "Лист1", i, j);
            }
        }


//        String empData[][] = {
//                {"abc12345", "30000", "40"},
//                {"xyz12345", "40000", "30"},
//                {"pqr12345", "80000", "50"}
//        };

        return empData;
    }

}
