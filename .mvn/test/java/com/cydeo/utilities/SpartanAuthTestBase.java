package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://34.226.136.145:7000";
    }
}
