package com.udemy.April082025;

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestBody {

    String studentId;

    //1.create post request body using  HashMap

    @Test
    void createStudentsUsingHashMap() {

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        String courses[] = {"C", "C++"}; //define an array and adding data
        data.put("courses", courses); // adding that into hashmap


        studentId = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", equalTo("application/json"))
                .log().all()
                .extract().jsonPath().getString("id");

    }


    //2.create post request body using  org.json Library

    @Test
    void createStudentsUsingJsonLibrary() {

        JSONObject data = new JSONObject(); //create a object "data" using JsonObject
        data.put("name" ,"Scott");
        data.put("location","France");
        data.put("phone","123456");

        String courses[] ={"C" ,"C++"}; //array of strings
        data.put("courses",courses);

        studentId = given()
                .contentType("application/json")
                .body(data.toString()) //converts data to string

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", equalTo("application/json"))
                .log().all()
                .extract().jsonPath().getString("id");

    }

    //Create Post Request Body using  POJO Class

    @Test
    void createStudentsUsingPojoClass() {

        StudentsPOJO data = new StudentsPOJO();
        data.setName("Scott");                 //setting variables using setName
        data.setLocation("France");
        data.setPhone("123456");

        String courses[] = {"C", "C++"};
        data.setCourses(courses);

        studentId = given()
                .contentType("application/json")
                .body(data) //converts data to string

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo(data.getName()))        //Retrieving data using getName
                .body("location", equalTo(data.getLocation()))
                .body("phone", equalTo(data.getPhone()))
                .body("courses[0]", equalTo(data.getCourses()[0]))
                .body("courses[1]", equalTo(data.getCourses()[1]))
                .header("Content-Type", equalTo("application/json"))
                .log().all()
                .extract().jsonPath().getString("id");
    }


    @AfterMethod
    void deleteStudentRecord()
    {
        given()
        .when()
                .delete("http://localhost:3000/students/" + studentId)
        .then()
                .statusCode(200);

    }

    }

