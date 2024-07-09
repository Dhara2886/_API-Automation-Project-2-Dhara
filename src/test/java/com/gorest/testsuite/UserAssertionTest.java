package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // Verify if the total record is 20
    @Test
    public void test001() {
        response.body("", hasSize(20));
    }

    // Verify if the name of id = 7018223 is equal to ”Aryan Varman”
    @Test
    public void test002() {
        response.body("find{it.id == 7018223}.name", equalTo("Aryan Varman"));
    }

    // Check the single ‘Name’ in the Array list (Devasree Varrier)
    @Test
    public void test003() {
        response.body("name", hasItem("Devasree Varrier"));
    }

    // Check the multiple ‘Names’ in the ArrayList ("Chandraprabha Bhattacharya", "Shanti Banerjee", "Rep. Abhaidev Somayaji")
    @Test
    public void test004() {
        response.body("name", hasItems("Chandraprabha Bhattacharya", "Shanti Banerjee", "Rep. Abhaidev Somayaji"));
    }

    // Verify the email of userid = 7018223 is equal “aryan_varman@wilkinson-tremblay.test”
    @Test
    public void test005() {
        response.body("find{it.id == 7018223}.email", equalTo("aryan_varman@wilkinson-tremblay.test"));
    }

    // Verify the status is “inActive” of user name is “Aryan Varman”
    @Test
    public void test006() {
        response.body("find{it.name == 'Aryan Varman'}.status", equalTo("inactive"));
    }


    // Verify the Gender = female of user name is “Gorakhanatha Pillai”
    @Test
    public void test007() {
        response.body("find{it.name == 'Anjushri Kakkar'}.gender", equalTo("female"));
    }

}
