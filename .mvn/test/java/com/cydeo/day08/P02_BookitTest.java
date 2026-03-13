package com.cydeo.day08;

import com.cydeo.utilities.BooKitUtilities;
import com.cydeo.utilities.BookitBaseURL;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class P02_BookitTest extends BookitBaseURL {

    String email = "example1@gmsil.com";
    String password = "password";
    String accessToken = BooKitUtilities.getToken(email,password);

    @DisplayName("GET /api/campuses")
    @Test
    public void test1() {
        given()
                .log().headers()
                .accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when()
                .get("/api/capuses")
                .then()
                .statusCode(200)
                .log().body();
    }
    //create UTIL class that will create/generate the token based on the given email and password

    @DisplayName("GET /api/campuses")
    @Test
    public void test2() {
        given()
                .log().headers()
                .accept(ContentType.JSON)
                .header("Authorization", BooKitUtilities.getToken(email,password))
                .when()
                .get("/api/users/me")
                .then()
                .statusCode(200)
                .log().body();
    }
}
