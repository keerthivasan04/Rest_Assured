package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class testsOnLocalAPI {

    //    @Test
    public void getRequest() {
        baseURI = "http://localhost:3000";
        given().get("/users").then().statusCode(200).log().all();       //ok
    }

    //    @Test
    public void post() {
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Venkat");
        jsonObject.put("lastName", "S");
        given().body(jsonObject.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().post("/users")
                .then().statusCode(201).log().all();        //created
    }

    //    @Test
    public void put() {
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Madan");
        jsonObject.put("lastName", "L");
        given().body(jsonObject.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().put("/users/16")                                // id = 16 user will be updated
                .then().statusCode(200).log().all();        //update
    }

    //    @Test
    public void patch() {                        //partial update
        baseURI = "http://localhost:3000";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lastName", "N");
        given().body(jsonObject.toJSONString()).contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().patch("/users/11")                              // id = 11 user lastname will be updated
                .then().statusCode(200).log().all();        //update
    }

    @Test
    public void delete() {                  // delete the item
        baseURI = "http://localhost:3000";
        given().delete("/users/16")     //id = 16 user is deleted
                .then().statusCode(200).log().all();        // delete
    }
}
