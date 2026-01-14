package com.example.petstore.tests;

import com.example.petstore.config.TestConfig;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SmokeTest extends TestConfig {

    @Test
    void shouldReturnPetById_whenPetExistsOrNotFoundIsHandled() {
        // Берём любой id. На публичном petstore данные плавающие,
        // поэтому в smoke можно проверить "не 500" и формат ответа.
        given()
        .when()
            .get("/pet/{id}", 1)
        .then()
            .statusCode(anyOf(is(200), is(404)));
    }
}