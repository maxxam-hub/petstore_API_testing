package com.example.petstore.client;

import com.example.petstore.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetClient {

    public Response createPet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");
    }

    public Response getPet(long id) {
        return given()
                .when()
                .get("/pet/{id}", id);
    }
}