package com.udemy.April132025;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTests {

    //1.Basic Authentication

    @Test

    void verifyBasicAuth()
    {
        given()
                .auth().basic("postman","password")

        .when()
                .get("https://postman-echo.com/basic-auth")

        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().body();




    }
}
