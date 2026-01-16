package com.example.petstore.tests.store;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.PetClient;
import com.example.petstore.client.StoreClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.OrderTestData;
import com.example.petstore.data.PetTestData;
import com.example.petstore.model.Order;

public class StoreOrderCreateTests extends TestConfig {

    private final StoreClient storeClient = new StoreClient();
    private final PetClient petClient = new PetClient();

    long petId = petClient.createPet(PetTestData.defaultPet())
        .then()
        .statusCode(200)
        .extract()
        .path("id");

    @Test
    void shouldPlaceOrder() {

        Order order = OrderTestData.defaultOrder(petId);

        storeClient.placeOrder(order)
                .then()
                .statusCode(200)
                .body("petId", equalTo((long) order.petId))
                .body("status", equalTo(order.status));
    }

    @Test
    void shouldNotPlaceOrderWithNegativeQuantity() {
        Order order = OrderTestData.orderWithNegativeQuantity();

        storeClient.placeOrder(order)
                .then()
                .statusCode(200);
    }
}   
