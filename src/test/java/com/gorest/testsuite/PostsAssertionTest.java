package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // Verify that if the total record is 25
    @Test
    public void test001() {
        response.body("", hasSize(25));
    }

    // Verify that if the title of id = 140337 is equal to ”Suffragium quae undique quia error creo.”
    @Test
    public void test002() {
        response.body("find{it.id == 140337}.title", equalTo("Suffragium quae undique quia error creo."));
    }

    // Check that single user_id in the Array list (7015118)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7018220));
    }

    // Check that multiple ids in the ArrayList (7018220, 7018217, 7018216)
    @Test
    public void test004() {
        response.body("user_id", hasItems(7018220, 7018217, 7018216));
    }

    // Verify the body of userid = 7018214 is equal "Tondeo recusandae cornu. Laborum cernuus urbs. Cedo trado est. Ipsum sperno careo. Tergo amicitia cunae. Cetera tabesco benigne. Deinde capillus valens. Inflammatio tempore consequatur. Absconditus corona contabesco. Artificiose vita volup. Voluptatem utrimque videlicet. Suffoco terror summopere."
    @Test
    public void test005() {
        response.body("find{it.user_id == 7018214}.body", equalTo("Tondeo recusandae cornu. Laborum cernuus urbs. Cedo trado est. Ipsum sperno careo. Tergo amicitia cunae. Cetera tabesco benigne. Deinde capillus valens. Inflammatio tempore consequatur. Absconditus corona contabesco. Artificiose vita volup. Voluptatem utrimque videlicet. Suffoco terror summopere."));
    }
}
