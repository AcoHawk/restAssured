package com.cydeo.day04;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_SpartansWithJsonPath  extends SpartansTestBase {

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
        //response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("data.id");
        String name = jsonPath.getString("data.name");
        String gender = jsonPath.getString("data.gender");
        String phone = jsonPath.getString("data.phone");

        assertEquals(5747,id);
        assertEquals("Santo",name);
        assertEquals("Female",gender);
        assertEquals("744848645267",phone);
        assertEquals("744848645267",jsonPath.getString("data.phone"));
    }

    @Test
    public void test() {
        assertEquals(9,6+3);
    }
}
