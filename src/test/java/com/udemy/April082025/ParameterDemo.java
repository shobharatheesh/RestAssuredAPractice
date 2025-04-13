package com.udemy.April082025;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParameterDemo {

    @Test
    void pathParam()
    {
        given()
                .pathParam("country","India")  //Path Parameter

        .when()
                .get("https://restcountries.com/v2/name/{country}")

        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void queryParams()
    {

        given()
                .queryParam("page","2")
                .queryParam("id","5")

        .when()
                .get("https://reqres.in/api/users")
        .then()
                .statusCode(200)
                .log().body();




    }
}
