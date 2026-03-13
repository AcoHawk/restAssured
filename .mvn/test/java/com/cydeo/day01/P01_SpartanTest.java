package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {

    String spartanBaseUrl = "http://34.226.136.145:8000";

    //control N (shortcut)
    @Test
    public void getAllSpartans() {
        //send request spartan URL and get response as Response interface
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        //both methods do the same
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //verify status code 200
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);


        //printing the JSON response on the console
        response.prettyPrint();
        //response.print();
    }

}
