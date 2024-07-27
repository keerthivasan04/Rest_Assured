package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class TestExample {
    @Test
    public void test_1() {
        // GET request
        Response res = get("https://reqres.in/api/users?page=2");
        System.out.println(res.getStatusCode());
        System.out.println(res.getTime());
        System.out.println(res.body().asString());
        System.out.println(res.getHeader("content-type"));

        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test
    public void test_2() {
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then().statusCode(200);
        given().get("/users?page=2").then().statusCode(200)
                .body("data[1].id", equalTo(8)).log().all();            //log the response on the console
    }
}

