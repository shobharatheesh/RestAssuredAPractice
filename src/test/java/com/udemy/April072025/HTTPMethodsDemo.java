package com.udemy.April072025;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;  //accessing gherkin keywords given(), when(), then()
import static org.hamcrest.Matchers.*; //for validation


//pre-condition  --given() --content-type , headers , auth , params , cookies
//Action/steps  --when() --request , GET <POST <GET<DELETE
//Validation ---then()  --statuscode,headers , extraxt response , extraxt headers , extraxt cookies and response body

//different validations
//status code
//Response body
//Response Time
//content-type
//response string

//Request Payload is body --pre-condition -- given()


public class HTTPMethodsDemo {

    int userId;

    //1. GET ---> Test to retrieve the users and validate the response

    @Test(priority = 1 , enabled=false)
    void getUsers() {
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page", equalTo(2)) // verify the value
                .body(containsString("email"))  //verify field existence
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .time(lessThan(2000L))
                .log().all(); // display entire response in the console window
    }

    //2.POST creating a new user and validating

    @Test(priority = 2)
    void createUser() {

        HashMap<String, String> data = new HashMap<String, String>(); //key value pair
        data.put("name", "pavan");
        data.put("job", "trainer");

        userId = given()
                .contentType("application/json")
                .body(data)

        .when()
                .post("https://reqres.in/api/users")

        .then()
                .statusCode(201)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .time(lessThan(2000L))
                .body("name", equalTo("pavan"))
                .body("job", equalTo("trainer"))
                .body(containsString("id"))
                .log().all()
                .extract().jsonPath().getInt("id");  //capture entire response

    }

    //3.update the existing user and validate the response
    @Test(priority = 3)
    void updateUser() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", "Shobha");
        data.put("job", "Engineer");

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .put("https://reqres.in/api/users/" +userId)

        .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .time(lessThan(2000L))
                .body("name", equalTo("Shobha"))
                .body("job", equalTo("Engineer"))
                .log().all();


    }

    //4.delete the user and validate the response

    @Test(priority = 4)
    void deleteUser() {

        given()

        .when()
                .delete("https://reqres.in/api/users/")

        .then()
                .statusCode(204)
                .time(lessThan(2000L))
                .body(emptyOrNullString())
                .log().all();

    }
}
