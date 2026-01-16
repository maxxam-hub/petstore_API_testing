package com.example.petstore.tests.pet;

import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.PetTestData;
import com.example.petstore.model.Pet;

public class PetDeleteTests extends TestConfig {

    private long createPetAndReturnId() {
        Pet pet = PetTestData.defaultPet();
        return petClient.createPet(pet)
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    private final PetClient petClient = new PetClient();

    @Test
    void shouldDeleteExistingPet() {
        long petId = createPetAndReturnId();

        petClient.deletePet(petId)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingPet() {
        petClient.deletePet(System.currentTimeMillis())
                .then()
                .statusCode(404);
    }
}
