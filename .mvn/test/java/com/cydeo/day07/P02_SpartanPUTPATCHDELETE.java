package com.cydeo.day07;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P02_SpartanPUTPATCHDELETE  extends SpartansTestBase {

    @DisplayName("PUT SPARTAN WITH MAP")
    @Test
    public void test1() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe PUT");
        requestBody.put("gender","Male");
        requestBody.put("phone","8877445596");

        int id = 110;

        given()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBody)
                .put("api/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }


    @DisplayName("PATCH SPARTAN WITH MAP")
    @Test
    public void test2() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", "John Doe PATCH");
        int id = 110;

        given()
                .log().body()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBody)
                .when()
                .patch("api/spartans/{id}")
                .then()
                .log().all()
                .statusCode(200);

    }


    @DisplayName("Delete SPARTAN")
    public void test3() {
        int id = 110;

        given()
                .pathParam("id",id)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(200);

        //after deletion, when we send request to the id that is deleted ,status code should read 404

        given()
                .pathParam("id",id)
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(404);


    }
}
