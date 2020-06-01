package simple_tests;

import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class Demo {

    @Test
    void postsTest() {

        // Specify base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET, "/posts");

        // Print response in console
        String responseBody = response.getBody().asString();

        //System.out.println("Response body is: " + responseBody);

        // from stackoverflow
//        List<String> list = response.jsonPath().getList("");
//
//        System.out.println("List: " + list.get(0));

        List<Post> postsList = response.jsonPath().using((GsonObjectMapperFactory) (aClass, s) -> new GsonBuilder().setPrettyPrinting().create())
                .getList("", Post.class);

        System.out.println("postList: " + postsList.get(0).getTitle());
    }

    @Data
    class Post {
        private String userId;
        private String id;
        private String title;
        private String body;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
