package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class JSONSchemaValidator {

    @Test
    public void validateResponse() {

        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").then()
                .assertThat()
                //match the schema with the json present in the file in our local(expected) against the uri(actual)
                .body(matchesJsonSchemaInClasspath("schema.json.txt"))
                .statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Tobias"))
                .log().all();
    }
}
