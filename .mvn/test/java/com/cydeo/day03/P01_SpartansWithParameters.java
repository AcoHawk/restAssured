package com.cydeo.day03;
import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P01_SpartansWithParameters extends SpartansTestBase {

/*
Given Accept type is Json
and "id" parameter value is 24
when user sends GET request to api/spartans/{id}
then response status code should be 200
and response Content-Type should be application/json
and " Micheal  " should be in response payload (body) -24
 */

    @DisplayName("GET Spartan /api/spartans/{id} with valid ID")
    @Test
    public void test1(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                        //or "abc"
                .pathParam("id",5490)
                .when()
                                // or {abc}
                .get("/api/spartans/{id}");
        response.prettyPrint();


        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Michael"));
    }




    @DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test
    public void test2(){

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                //or "abc"
                .pathParam("id",54900)
                .when()
                // or {abc}
                .get("/api/spartans/{id}");
        response.prettyPrint();


        assertEquals("application/json",response.contentType());
        assertEquals(404,response.statusCode());
        assertTrue(response.body().asString().contains("Not Found"));
    }


    //shortcut control N
    @Test
    void test3() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Male")
                .and()
                .queryParam("nameContains", "e")
                .get("/api/spartans/search");

        response.prettyPrint();

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Male"));

        assertTrue(response.body().asString().contains("Michael"));

    }


    @Test
    void test4() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender","Male");
        queryMap.put("nameContains", "e");

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .get("/api/spartans/search");

        response.prettyPrint();

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Male"));

        assertTrue(response.body().asString().contains("Michael"));

    }


}
