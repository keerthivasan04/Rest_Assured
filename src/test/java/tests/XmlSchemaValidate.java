package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class XmlSchemaValidate{

    @Test
    public void schemaValidation() throws Exception {
        File file = new File("./soapRequest/Add.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

        baseURI = "http://www.dneonline.com";
        given().contentType("text/xml")
                .accept(ContentType.XML)
                .body(requestBody)
                .when().post("/calculator.asmx")
                .then().statusCode(200).log().all()
                //validate soap response body
                .and().body("//*:AddResult.text()", equalTo("10"))
                .and().assertThat()
                .body(matchesXsdInClasspath("CalculatorSchema.xsd"));
    }
}
