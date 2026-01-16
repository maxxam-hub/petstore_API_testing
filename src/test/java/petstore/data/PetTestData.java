package com.example.petstore.data;

import java.util.List;

import com.example.petstore.model.Category;
import com.example.petstore.model.Pet;
import com.example.petstore.model.Tag;

public class PetTestData {

    public static Pet defaultPet() {
        return new Pet(
                "Barsik",
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s")
        );
    }

    public static Pet updatedPet(long petId, String status) {
        Pet pet = defaultPet();
        pet.id = petId;
        pet.name = "Updated name";
        pet.status = status;
        return pet;
    }


    public static Pet fullSetPet() {
        return new Pet(
                randomId(),
                "Barsik",
                "available",
                category(),
                tags(),
                List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s"
                )
        );
    }

    public static Pet petWithStatus(String status) {
        Pet pet = defaultPet();
        pet.status = status;
        return pet;
    }

    public static Pet petWithInvalidStatus() {
        Pet pet = defaultPet();
        pet.status = "flying";
        return pet;
    }

    public static Pet petWithoutName() {
        return new Pet(
            null,
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s")
        );
    }

    public static Pet petWithoutPhotos() {
        return new Pet(
            "Barsik",
            null
        );
    }

    public static Pet petWithLongName() {
        return new Pet(
            "A".repeat(300),
            List.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK5CqiQQDLVEVd_mEtfKpqF8MTZj0SqiEEWg&s")
        );
    }

    private static Category category() {
        return new Category(1L, "Dogs");
    }

    private static List<Tag> tags() {
        return List.of(
            new Tag(1L, "cute"),
            new Tag(2L, "home")
        );
    }

    private static long randomId() {
        return System.currentTimeMillis();
    }
}