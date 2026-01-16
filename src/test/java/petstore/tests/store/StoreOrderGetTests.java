package com.example.petstore.tests.store;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.client.StoreClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.OrderTestData;
import com.example.petstore.data.PetTestData;
import com.example.petstore.model.Order;

public class StoreOrderGetTests extends TestConfig {

    private final StoreClient storeClient = new StoreClient();
    private final PetClient petClient = new PetClient();

    long petId = petClient.createPet(PetTestData.defaultPet())
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    @Test
    void shouldGetExistingOrder() {
        Order order = OrderTestData.defaultOrder(petId);

        long orderId = storeClient.placeOrder(order)
                .then()
                .extract()
                .path("id");

        storeClient.getOrder(orderId)
                .then()
                .statusCode(200)
                .body("id", equalTo(orderId));
    }

    @Test
    void shouldNotGetNonExistingOrder() {
        storeClient.getOrder(999999)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldNotGetOrderWithInvalidId() {
        storeClient.getOrder(-1)
                .then()
                .statusCode(404);
    }
}
