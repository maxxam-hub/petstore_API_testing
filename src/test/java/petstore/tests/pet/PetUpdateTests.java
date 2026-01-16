package petstore.tests.pet;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import petstore.client.PetClient;
import petstore.config.TestConfig;
import petstore.data.PetTestData;
import petstore.model.Pet;

public class PetUpdateTests extends TestConfig {

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
    void shouldUpdateExistingPet() {
        long petId = createPetAndReturnId();

        Pet updated = PetTestData.updatedPet(petId, "sold");

        petClient.updatePet(updated)
                .then()
                .statusCode(200)
                .body("name", equalTo(updated.name));
    }

    @Test
    void shouldUpsertPetWhenUpdatingNonExisting() {
        Pet pet = PetTestData.fullSetPet();

        petClient.updatePet(pet)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldUpdatePetWithFormData() {
        long petId = createPetAndReturnId();

        petClient.updatePetWithFormData(petId, "NewName", "sold")
                .then()
                .statusCode(200);
    }

    
}
