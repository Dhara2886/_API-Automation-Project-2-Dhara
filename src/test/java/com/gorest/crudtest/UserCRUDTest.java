package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static String name = "user1";
    static String email = TestUtils.getRandomValue() + "user12@gmail.com";
    static String gender = "female";
    static String status = "active";


    @Test
    public void test001() {


        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 37570f6e9db6e7a35dcfc5dd64c429e34ba1fb8dadd0e2c794f121d9c5b20601")
                .when()
                .body(userPojo)
                .post("/users");

        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void test002() {

        String name = "user1" + "Updated";
        String email = TestUtils.getRandomValue() + "user12@gmail.com";
        String gender = "female";
        String status = "active";

        int storeId = 7015034;
        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", storeId)
                .header("Authorization", "Bearer 37570f6e9db6e7a35dcfc5dd64c429e34ba1fb8dadd0e2c794f121d9c5b20601")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void test003() {
        given().log().ifValidationFails()
                .pathParam("id", 7015034)
                .header("Authorization", "Bearer 37570f6e9db6e7a35dcfc5dd64c429e34ba1fb8dadd0e2c794f121d9c5b20601")
                .when()
                .delete("/users/{id}")
                .then().log().ifValidationFails().statusCode(204);
        given()
                .pathParam("id", 7015034)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
}
