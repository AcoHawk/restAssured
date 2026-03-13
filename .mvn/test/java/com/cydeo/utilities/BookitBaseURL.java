package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class BookitBaseURL {
    @BeforeAll
    public static void init() {
        baseURI = "https://qa.bookit.cydeo.com/sign-in";
    }
}
