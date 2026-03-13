package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_NegativeSpartanTests {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://34.226.136.145:8000";
        //RestAssured.port = "/api/spartan";//perfix appliesmt
    }


    @DisplayName("GET - ALL SPARTANS")
    @Test
    public void getAllSpartans(){
        Response reponse = given()
                .accept("application/JSON")
                .when()
                .get("/api/spartan");//http://34.226.136.145:8000/api/spartan

        assertEquals(200,reponse.statusCode());
    }
    /*
    Given accept type application /xml
    when user send GET request to /api/spartans/10 end point
    then status code must be 406
    and response header for content-type must be application
     */
    @DisplayName("GET - ONE SPARTAN 406")
    @Test
    public void xmlTest(){
        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("/api/spartans/5213");

        //assertEquals(406,response.statusCode());
       assertEquals("application/xml;charset=UTF-8",response.header("Content-Type"));
    }
}
