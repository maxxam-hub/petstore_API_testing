package com.example.petstore.tests.store;

import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.client.StoreClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.OrderTestData;
import com.example.petstore.data.PetTestData;
import com.example.petstore.model.Order;

public class StoreOrderDeleteTests extends TestConfig {

    private final StoreClient storeClient = new StoreClient();
    private final PetClient petClient = new PetClient();

    long petId = petClient.createPet(PetTestData.defaultPet())
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    @Test
    void shouldDeleteExistingOrder() {
        Order order = OrderTestData.defaultOrder(petId);

        long orderId = storeClient.placeOrder(order)
                .then()
                .extract()
                .path("id");

        storeClient.deleteOrder(orderId)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotDeleteNonExistingOrder() {
        storeClient.deleteOrder(999999)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldNotDeleteOrderWithInvalidId() {
        storeClient.deleteOrder(-5)
                .then()
                .statusCode(404);
    }
}
