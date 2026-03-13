package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P02_HrWithParameter extends HrTestBase {

    @DisplayName("GET request to / countries with Region ID")

    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("region_id","20")
                .when()
                .get("/countries");
        response.prettyPrint();
        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("United States of America"));
    }
}
