package com.example.petstore.tests.e2e;

import java.io.File;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.client.StoreClient;
import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.OrderTestData;
import com.example.petstore.data.PetTestData;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.Order;
import com.example.petstore.model.Pet;
import com.example.petstore.model.User;

public class Tests extends TestConfig {

    private final PetClient petClient = new PetClient();
    private final StoreClient storeClient = new StoreClient();
    private final UserClient userClient = new UserClient();

    @Test
    void shouldCreatePetPlaceOrderAndDeleteOrder() {
        long petId = petClient.createPet(PetTestData.defaultPet())
                .then()
                .extract()
                .path("id");

        long orderId = storeClient.placeOrder(
                OrderTestData.defaultOrder(petId))
                .then()
                .extract()
                .path("id");

        storeClient.getOrder(orderId)
                .then()
                .statusCode(200);

        storeClient.deleteOrder(orderId)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateAndUpdatePet() {
        long petId = petClient.createPet(PetTestData.defaultPet())
                .then()
                .extract()
                .path("id");

        Pet updated = PetTestData.updatedPet(petId, "sold");

        petClient.updatePet(updated)
                .then()
                .statusCode(200);

        petClient.getPet(petId)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateGetAndDeleteUser() {
        User user = UserTestData.defaultUser();

        userClient.createUser(user)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .statusCode(200);

        userClient.deleteUser(user.username)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateUserLoginAndLogout() {
        User user = UserTestData.defaultUser();

        userClient.createUser(user)
                .then()
                .statusCode(200);

        userClient.login(user.username, user.password)
                .then()
                .statusCode(200);

        userClient.logout()
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateAndUpdateUser() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        user.firstName = "Updated";

        userClient.updateUser(user.username, user)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Updated"));
    }

    @Test
    void shouldBuyPetHappyPath() {

        Pet pet = PetTestData.fullSetPet();
        long petId = petClient.createPet(pet)
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        petClient.getPet(petId)
                .then()
                .statusCode(200)
                .body("status", equalTo("available"));

        Order order = OrderTestData.defaultOrder(petId);
        long orderId = storeClient.placeOrder(order)
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        storeClient.getOrder(orderId)
                .then()
                .statusCode(200)
                .body("petId", equalTo(petId))
                .body("quantity", equalTo(1));

        Pet soldPet = PetTestData.updatedPet(petId, "sold");
        petClient.updatePet(soldPet)
                .then()
                .statusCode(200);

        petClient.getPet(petId)
                .then()
                .statusCode(200)
                .body("status", anyOf(equalTo("sold"), equalTo("available")));

        storeClient.deleteOrder(orderId)
                .then()
                .statusCode(200);

        storeClient.getOrder(orderId)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldHandleOrderForSoldPet() {
        Pet soldPet = PetTestData.fullSetPet();
        soldPet.status = "sold";
        long petId = petClient.createPet(soldPet)
                .then()
                .extract()
                .path("id");

        Order order = OrderTestData.defaultOrder(petId);

        storeClient.placeOrder(order)
                .then()
                .statusCode(200);

        petClient.getPet(petId)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateTwoIndependentOrders() {
        long pet1 = petClient.createPet(PetTestData.defaultPet())
                .then()
                .extract()
                .path("id");

        long pet2 = petClient.createPet(PetTestData.defaultPet())
                .then()
                .extract()
                .path("id");

        long order1 = storeClient.placeOrder(OrderTestData.defaultOrder(pet1))
                .then()
                .extract()
                .path("id");

        long order2 = storeClient.placeOrder(OrderTestData.defaultOrder(pet2))
                .then()
                .extract()
                .path("id");

        storeClient.getOrder(order1)
                .then()
                .body("petId", equalTo(pet1));

        storeClient.getOrder(order2)
                .then()
                .body("petId", equalTo(pet2));

        storeClient.deleteOrder(order1);
        storeClient.deleteOrder(order2);
    }

    @Test
    void shouldCreateUploadImageAndUpdatePet() {
        Pet pet = PetTestData.defaultPet();
        long petId = petClient.createPet(pet)
                .then()
                .extract()
                .path("id");

        File image = new File("src/test/resources/test.jpg");

        petClient.uploadImage(petId, image, "pet image")
                .then()
                .statusCode(200);

        Pet updated = PetTestData.updatedPet(petId, "available");
        updated.name = "Updated Pet";

        petClient.updatePet(updated)
                .then()
                .statusCode(200);

        petClient.getPet(petId)
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Pet"));

        petClient.deletePet(petId);
    }

    @Test
    void shouldHandleUserLifecycle() {
        User user = UserTestData.defaultUser();

        userClient.createUser(user)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .statusCode(200);

        userClient.login(user.username, user.password)
                .then().statusCode(200)
                .body("message", containsString("logged in"));

        user.firstName = "Updated";
        userClient.updateUser(user.username, user)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .body("firstName", equalTo("Updated"));

        userClient.logout()
                .then()
                .statusCode(200);

        userClient.deleteUser(user.username);
    }

    @Test
    void shouldHandleDuplicateUserCreation() {
        User user = UserTestData.defaultUser();

        userClient.createUser(user);
        userClient.createUser(user)
                .then().statusCode(200);

        userClient.getUserByUsername(user.username)
                .then().statusCode(200);

        userClient.deleteUser(user.username);
    }

}
