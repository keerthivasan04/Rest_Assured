package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class getAndPostExamples {

//    @Test
    public void testGet(){
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then()
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Tobias"));
    }

    @Test
    public void testPost(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("name","morpheus");
//        map.put("job","leader");
//        System.out.println(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","morpheus");
        jsonObject.put("job","leader");
        System.out.println(jsonObject.toJSONString());

        baseURI = "https://reqres.in/api";
        given().body(jsonObject.toJSONString())
                .header("Content-Type","application/json")      //header to the content
                .contentType(ContentType.JSON)          // content type as JSON format
                .accept(ContentType.JSON)               // accept only JSON type content type
                .when().post("/users")
                .then().statusCode(201).log().all();        //created
    }
}
