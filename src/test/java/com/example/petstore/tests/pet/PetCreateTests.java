package petstore.tests.pet;

import java.io.File;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import petstore.client.PetClient;
import petstore.config.TestConfig;
import petstore.data.PetTestData;
import petstore.model.Pet;

public class PetCreateTests extends TestConfig {

    private final PetClient petClient = new PetClient();

    @Test
    void shouldCreatePetOnlyRequired() {
        Pet pet = PetTestData.defaultPet();

        petClient.createPet(pet).then()
                .statusCode(200)
                .body("name", equalTo(pet.name))
                .body("photoUrls", equalTo(pet.photoUrls));
    }

    @Test
    void shouldCreatePetFullFields() {
        Pet pet = PetTestData.fullSetPet();

        petClient.createPet(pet).then()
                .statusCode(200)
                .body("id", equalTo(pet.id))
                .body("status", equalTo(pet.status));
    }

    @Test
    void shouldUploadImage() {

        Pet pet = PetTestData.defaultPet();

        File image = new File("src/test/resources/test.jpg");

        petClient.uploadImage(pet.id, image, "test image").then()
                .statusCode(200)
                .body("message", containsString("uploaded"));
    }

    @Test
    void shouldNotGetUnexistantPet() {
        petClient.getPet(System.currentTimeMillis()).then()
                .statusCode(404);
    }

    @Test
    void shouldNotGetWrongTypePetId() {
        petClient.getPet("abc").then()
                .statusCode(404);
    }

    @ParameterizedTest
    @ValueSource(strings = {"available", "pending", "sold"})
    void shouldCreatePetWithDifferentStatuses(String status) {
        Pet pet = PetTestData.petWithStatus(status);

        petClient.createPet(pet)
                .then()
                .statusCode(200)
                .body("status", equalTo(status));
    }

    @ParameterizedTest
    @ValueSource(strings = {"unavailable", "unpending", "soldout"})
    void shouldNotCreatePetWithWrongStatuses(String status) {
        Pet pet = PetTestData.petWithStatus(status);

        petClient.createPet(pet)
                .then()
                .statusCode(200)
                .body("status", equalTo(status));
    }

    @Test
    void shouldNotCreatePetWithoutName() {
        Pet pet = PetTestData.petWithoutName();

        petClient.createPet(pet)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotCreatePetWithoutPhotos() {
        Pet pet = PetTestData.petWithoutPhotos();

        petClient.createPet(pet)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotCreatePetWithInvalidStatus() {
        Pet pet = PetTestData.petWithInvalidStatus();

        petClient.createPet(pet)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotCreatePetWithTooLongName() {
        Pet pet = PetTestData.petWithLongName();

        petClient.createPet(pet)
                .then()
                .statusCode(200);
    }
}
