package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {


    String spartanBaseUrl = "http://34.226.136.145:8000";
    /*
    * Given content type is application/json
    * When user sends GET request to /api/spartans endpoint
    * Then status code should be 200
    * And Content type should be applications/json
    * And response body needs to contain Xim
     */

    @Test
    public void getAllSpartans(){
        Response response = RestAssured.given()
                //.accept("application/json")
                .accept(ContentType.JSON) // hey api send me json response
                .when()//Syntactic sugar
                .get(spartanBaseUrl + "/api/spartans");
        //print response body
        //response.prettyPrint();

        //how to get the staus code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how to get the response header for content
        String actualContentType = response.contentType();

        System.out.println("actualContentType = " + actualContentType);

        //assert the content type header
        //Assertions.assertEquals("application/json",actualContentType);
        Assertions.assertEquals(ContentType.JSON.toString(),actualContentType);

        //for any response header we can use the header ("headerName") method which will give that header's values as a string
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Content-Type"));
        System.out.println(response.header("Date"));

        //how to check
        boolean dateExits = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(dateExits);
    }

    @Test
    public void getSpartan(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(spartanBaseUrl + "/api/spartans/5213");

        //verify the status code
        Assertions.assertEquals(200,response.statusCode());

        //verify the contentType
        Assertions.assertEquals("application/json",response.contentType());
        //Assertions.assertEquals("application/json",response.getContentType());
        //Assertions.assertEquals("application/json",response.header("Content-Type"));
        //Assertions.assertEquals(ContentType.JSON.toString(),response.header("Content-Type"));

        response.prettyPrint();

        // verify that response body contains "Xim"
        Assertions.assertTrue(response.body().asString().contains("Xim"));

    }

    @Test
    public void helloSpartan(){
       Response response = RestAssured.when().get(spartanBaseUrl + "/api/hello");

        //print response
        response.prettyPrint();
        //verify the statuscode
        Assertions.assertEquals(200,response.statusCode());

        //contains type header should be "text/plain;charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //headers should contain Date
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //Content-Length should be 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        //response body should be "Hello World!"
        Assertions.assertTrue(response.body().asString().equals("Hello from Sparta"));
    }

}
