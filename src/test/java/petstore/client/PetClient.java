package com.example.petstore.client;

import java.io.File;
import java.util.List;

import com.example.petstore.model.Pet;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetClient {

    public Response createPet(Pet pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");
    }

    public Response updatePet(Pet pet) {
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

    public Response getPet(String id) {
        return given()
                .when()
                .get("/pet/{id}", id);
    }

    public Response uploadImage(long petId, File file, String metadata) {
        return given()
            .multiPart("file", file)
            .multiPart("additionalMetadata", metadata)
            .when()
            .post("/pet/{petId}/uploadImage", petId);
    }

    public Response findPetsByStatus(List<String> statuses) {
        return given()
            .queryParam("status", statuses)
            .when()
            .get("/pet/findByStatus");
    }

    public Response updatePetWithFormData(long petId, String name, String status) {
    return given()
            .contentType(ContentType.URLENC)
            .formParam("name", name)
            .formParam("status", status)
            .when()
            .post("/pet/{petId}", petId);
    }

    public Response deletePet(long petId, String apiKey) {
        return given()
            .header("api_key", apiKey)
            .when()
            .delete("/pet/{petId}", petId);
    }

    public Response deletePet(long petId) {
        return given()
            .when()
            .delete("/pet/{petId}", petId);
    }

    public Response deletePet(String petId) {
        return given()
            .when()
            .delete("/pet/{petId}", petId);
    }
}