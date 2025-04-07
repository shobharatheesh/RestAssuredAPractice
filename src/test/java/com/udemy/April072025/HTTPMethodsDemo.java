package com.udemy.April072025;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HTTPMethodsDemo {

    //Test to retrieve the users and validate the response

    @Test(priority = 1)

    void getUsers()
    {
                given()
                .when()
                      .get("https://reqres.in/api/users?page=2")
                .then()
                        .statusCode(200)
                        .body("page",equalTo(2))
                        .body(containsString("email"))
                        .header("Content-Type",equalTo("application/json; charset=utf-8"))
                        .time(lessThan(2000L));



    }


}
