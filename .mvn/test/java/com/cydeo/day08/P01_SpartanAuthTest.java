package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class P01_SpartanAuthTest extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as GUEST user ---expected 401")
    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(401)
                .body("httpStatus",is("UNAUTHORIZED"))
                .log().body();
    }

    @DisplayName("GET /api/spartans as USER --> expected 200")
    @Test()
    public void test2(){
        given()
                .log().headers()
                .accept(ContentType.JSON)
                .auth().preemptive().basic("user","user")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .log().body();
    }
    /*
    1.GET /api/spartans (without credentials)--> 401(with a "WWW-Authentication" header in the response)
    The server will respond saying "i need authentication
     2- GET api/spartamns (with the credentials - Authorization header)-->200 ok

     but if the server dosent send this authentication challenge correctly our credentials might not be sent at all
     and the request might fail.

     with preemptive
     */

    @DisplayName("GET api/spartans")// as editor--> 403
    @Test
    public void test3(){
                given()
                        .pathParam("id",381)
                        .auth().preemptive().basic("editor","editor")
                        .when()
                        .delete("/api/spartans/{id}")
                        .then()
                        .statusCode(403)
                        .body("error",is("Forbidden"))
                        .log().body();
    }

    @DisplayName("GET api/spartans")// as admin--> 200
    @Test
    public void test4(){
        given()
                .pathParam("id",381)
                .auth().preemptive().basic("admin","admin")
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body("error",is("Forbidden"))
                .log().body();
    }
}
