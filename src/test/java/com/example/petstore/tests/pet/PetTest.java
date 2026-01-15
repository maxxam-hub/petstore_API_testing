package com.example.petstore.tests.pet;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.PetTestData;
import com.example.petstore.model.Pet;

import io.restassured.response.Response;

public class PetTest extends TestConfig {

    PetClient petClient = new PetClient();

    @Test
    void shouldCreateAndGetPet() {
        // GIVEN
        Pet pet = PetTestData.defaultPet();

        // WHEN
        Response createResponse = petClient.createPet(pet);

        // THEN
        createResponse.then()
                .statusCode(200)
                .body("name", equalTo(pet.name))
                .body("status", equalTo(pet.status));

        // AND WHEN
        Response getResponse = petClient.getPet(pet.id);

        // AND THEN
        getResponse.then()
                .statusCode(200)
                .body("id", equalTo((long) pet.id));
    }
}