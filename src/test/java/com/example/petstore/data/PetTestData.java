package com.example.petstore.data;

import com.example.petstore.model.Pet;

public class PetTestData {

    public static Pet defaultPet() {
        return new Pet(
                System.currentTimeMillis(),
                "Barsik",
                "available"
        );
    }
}