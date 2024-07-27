package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class putPatchAndDelete {

    public void jsonObject(JSONObject jsonObject){
        jsonObject.put("name", "morpheus");
        jsonObject.put("job", "zion resident");
        System.out.println(jsonObject.toJSONString());
    }
    @Test
    public void testPost() {
        JSONObject jsonObject = new JSONObject();
        jsonObject(jsonObject);
        baseURI = "https://reqres.in/api";
        given().body(jsonObject.toJSONString())
                .header("Content-Type", "application/json")      //header to the content
                .contentType(ContentType.JSON)          // content type as JSON format
                .accept(ContentType.JSON)               // accept only JSON type content type
                .when().put("/users/2")
                .then().statusCode(200).log().all();        //update
    }

    @Test
    public void testPatch(){
        JSONObject jsonObject = new JSONObject();
        jsonObject(jsonObject);
        baseURI = "https://reqres.in/api";
        given().body(jsonObject.toJSONString())
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().patch("/users/2")
                .then().statusCode(200).log().all();        //update
    }

    @Test
    public void testDelete(){
//        JSONObject jsonObject = new JSONObject();
//        jsonObject(jsonObject);
//        baseURI = "https://reqres.in/api";
//        given().body(jsonObject.toJSONString())
//                .contentType(ContentType.JSON).accept(ContentType.JSON)
//                .when().delete("/users/2")
//                .then().statusCode(204).log().all();    //successful delete - 204

        //this is well and good for delete

        baseURI = "https://reqres.in/api";
        when().delete("/users/2")
                .then().statusCode(204).log().all();    //successful delete - 204
    }
}
