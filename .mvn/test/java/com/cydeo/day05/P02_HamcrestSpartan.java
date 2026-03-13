package com.cydeo.day05;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class P02_HamcrestSpartan extends SpartansTestBase {

    @DisplayName("GET SINGLE SPARTAN WITH HAMCREST")
    @Test
    public void test1() {
        given()
                .accept("application/json")
                .pathParam("id",5215)
        .when()
                .get("api/spartans/{id}")
        .then()
                .statusCode(200)
                //.statusCode(is(200))
                .contentType("application/json")
                .body("data.id",is(5215),
                        "data.name",is("Meta"),
                        "data.gender",is("Female"),
                        "data.phone",is("1938695106"));
    }

    @DisplayName("GET SINGLE SPARTAN WITH HAMCREST-SECOND WAY WITH SYNTACTIC SUGAR?FILLER KEYWORDS")
    @Test
    public void test2(){
        given()
                .accept("application/json")
                .and()
                .pathParam("id",15)
                .when()
                .get("api/spartans/{id")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .assertThat()
                .body("data.id",is(15))
                .and()
                .body("data.name",is("Meta"))
                .body("data.gender",is("Female"))
                .body("data.phone",is("1938695106"));
    }

    @DisplayName("GET SINGLE SPARTAN WITH HAMCREST - LOGS")
    @Test
    public void test3() {
        given()
                .log().parameters() //log on console .log().all() or specific items .header() , .ifValidationFails
                .accept("application/json")
                .pathParam("id",15)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                //.statusCode(is(200))
                .contentType("application/json")
                .body("data.id",is(15),
                        "data.name,",is("Meta"),
                        "data.gender",is("Female"),
                        "data.phone",is("1938695106"));
    }

    /*
    REQUEST LOGS:

    given()
            .log().all()--> Prints all requestd
                  .method()
                  .uri()
                  .parameters()
                  .body()
                  .header()...

   RESPONSE LOGS:

   then()
        .log().all()-->
     */

    @DisplayName("GET SINGLE SPARTAN WITH HAMCREST EXTRACT-RESPONSE")
    @Test
    public void test4() {
        Response response = given()
                .accept("application/json")
                .pathParam("id",5215)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                //.statusCode(is(200))
                .contentType("application/json")
                .body("data.id",is(5215),
                        "data.name",is("Han B41 Gr6"),
                        "data.gender",is("Female"),
                        "data.phone",is("88437742167"))
                .extract().response();
        response.statusCode();

        int id = response.path("data.id");
        System.out.println("id = " + id);

        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath.getInt(\"data.id\") = " + jsonPath.getInt("data.id"));

    }



public void test5() {
    JsonPath jsonPath = given()
            .accept("application/json")
            .pathParam("id",5215)
            .when()
            .get("api/spartans/{id}")
            .then()
            .statusCode(200)
            //.statusCode(is(200))
            .contentType("application/json")
            .body("data.id",is(5215),
                    "data.name",is("Han B41 Gr6"),
                    "data.gender",is("Female"),
                    "data.phone",is("88437742167"))
                    .extract().jsonPath();
            //.extract().response().jsonPath();
    //response.statusCode();

    //JsonPath jsonPath = response.jsonPath();

    //actual data from API response
    int id = jsonPath.getInt("data.id");
    String name = jsonPath.getString("data.name");

    //expected data from the database (retrieved using BD utilities)
    int expectedIdDB = 15;
    String expectedName = "Meta";

    //comparing API response with database values
    //we can use Hamcrest or Junit 5 assertions
    assertThat(id,is(expectedIdDB));
    assertThat(name,is(expectedName));

    //Junit5
    Assertions.assertEquals(expectedIdDB,id);
    Assertions.assertEquals(expectedName,name);

    /*
    HOW TO EXTRACT DATA AFTER VALIDATION USING then() AND HAMCREST?

    extract() --> it allows us to STORE data after doing verification.

    We can extract data in the following ways:

    1. response() --> to get the response object.
    Ex:
    extract().response();

    2.jsonPath() --> to get the response body as JsonPath object directly.
    Ex:
    extract.response().jsonPath()
    or
    extract().jsonPath()
     */

    /*
        when do we need to extract data when we can verify everything
        (status code , headers body) directly using then() and Hamcrest matchers?

        -In some cases, we need to compare API response data with values from a database or UI.
        -After completing API validation we may need to retrieve specific fields (for ex list of names, IDs)
        for more verification against BD/UI.
        -So we extract the response as a Response or JsonPath object to access the required data.
     */
    


}

}
