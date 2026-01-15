package com.example.petstore.data;

import java.util.List;

import com.example.petstore.model.Category;
import com.example.petstore.model.Pet;
import com.example.petstore.model.Tag;

public class PetTestData {

    public static Pet defaultPet() {
        return new Pet(
                System.currentTimeMillis(),
                "Barsik",
                "available",
                new Category(System.currentTimeMillis(), "cat"),
                List.of(new Tag(System.currentTimeMillis(), "animal")),
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s")
        );
    }
}