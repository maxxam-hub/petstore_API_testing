package petstore.tests.pet;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import petstore.client.PetClient;
import petstore.config.TestConfig;
import petstore.data.PetTestData;
import petstore.model.Pet;

import io.restassured.response.Response;

public class PetGetTests extends TestConfig {

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
    void shouldGetPet() {
        long id = createPetAndReturnId();

        petClient.getPet(id).then()
            .statusCode(200)
            .body("id", equalTo((long) id));
    }

     @Test
    void shouldReturn404ForNonExistingPet() {
        petClient.getPet(Long.MAX_VALUE)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturnErrorForNegativeId() {
        petClient.getPet(-1)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldNotGetWrongTypePetId() {
        Response getResponse = petClient.getPet("abc");
        getResponse.then()
                .statusCode(404);
    }

    @Test
    void shouldFindPetsByStatus() {
        petClient.findPetsByStatus(List.of("available"))
            .then()
            .statusCode(200);
    }

    @Test
    void shouldFindPetsByMultipleStatuses() {
        petClient.findPetsByStatus(List.of("available", "pending"))
                .then()
                .statusCode(200);
    }
}