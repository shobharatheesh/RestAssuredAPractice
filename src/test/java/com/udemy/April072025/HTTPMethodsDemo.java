package com.udemy.April072025;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HTTPMethodsDemo {

    //1. GET ---> Test to retrieve the users and validate the response

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
                        .time(lessThan(2000L))
                        .log().all();
    }

    //2.POST creating a new user and validating

    @Test(priority = 2)
    void createUsers()
    {

        HashMap<String, String> data = new HashMap<String,String>();
        data.put("name","pavan");
        data.put("job","trainer");

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .statusCode(201)
                .header("Content-Type",equalTo("application/json; charset=utf-8"))
                .time(lessThan(2000L))
                .body("name",equalTo("pavan"))
                .body("job",equalTo("trainer"))
                .body(containsString("id"))
                .log().all();






    }

}
