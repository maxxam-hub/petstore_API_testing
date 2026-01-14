package com.example.petstore.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestConfig {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "https://petstore.swagger.io/v2");
    }
}