package com.cydeo.day03;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class P03_SpartanWithResponsePath extends SpartansTestBase {

    @DisplayName("GET ALL SPARTANS - Verify success message")
    @Test
    void test1() {

        Response response = get("api/spartans");

        //response.prettyPrint();

        assertEquals(200, response.statusCode());

        String message = response.path("message");
        System.out.println("message = " + message);

        assertEquals("Successfully retrieved all the Spartans.", message);
    }

    /*
    Given accept type is "application/json"
    and path para id is 10
    when user sends a GET request to "api/spartans/{id}"
    Then status code should be 200
    and content-Type should be "application/json"
    and the "data" of the response body value must match the following
        id:5747
        name:"Santo"
        gender:"Female"
        phone:"744848645267"

     */
    @DisplayName("GET SPARTAN WITH RESPONSE PATH")
    @Test
    void test2() {

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("id", 5747)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());


        int id = response.path("data.id");
        String name = response.path("data.name");
        String gender = response.path("data.gender");
        String phone = response.path("data.phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(5747, id);
        assertEquals("Santo", name);
        assertEquals("Female", gender);
        assertEquals("744848645267", phone);

    }

    @Test
    void test3() {
        Response response = get("api/spartans");


        int firstID = response.path("data[5214].id");

        System.out.println("firstID = " + firstID);

        int idFirst = response.path("data.id[5214]");
        System.out.println("idFirst = " + idFirst);


        System.out.println("response.path(\"data[5214].name\") =" + response.path("data[5214].name"));

        System.out.println("response.path(\"data[5215].name\") =" + response.path("data[5215].name"));

        System.out.println("response.path(\"data[-1].name\") =" + response.path("data[-1].name"));
        System.out.println("response.path(\"data[-2].name\") = " + response.path("data[-2].name"));


        System.out.println("response.path(\"data[-2].name\") =" + response.path("data[-2].name"));

        List<String> allNames = response.path("data.name");
        System.out.println("allNames");

        for (String eachName : allNames) {
            System.out.println(eachName);
        }
    }
}

