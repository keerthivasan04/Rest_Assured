package tests;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SoapXmlRequest {

    // soap web services
    // https://www.freeformatter.com/xpath-tester.html#before-output - xml xpath finder

//    @Test
    public void validateSoapRequest() throws Exception {

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
                .and().body("//*:AddResult.text()", equalTo("10"));

    }

    @Test
    public void validateSoapRequest_2() throws IOException {
        File file = new File("./soapRequest/Subtract.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

        baseURI = "https://ecs.syr.edu/";
        given().contentType("text/xml").accept(ContentType.XML)
                .body(requestBody)
                .when().post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx")
                .then().statusCode(200).log().all()
                //validate soap response body
                .and().body("//*:SubtractResult.text()", equalTo("-5"));
    }
}
