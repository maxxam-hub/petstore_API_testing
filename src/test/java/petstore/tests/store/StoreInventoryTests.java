package com.example.petstore.tests.store;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.StoreClient;
import com.example.petstore.config.TestConfig;

public class StoreInventoryTests extends TestConfig {

    private final StoreClient storeClient = new StoreClient();

    @Test
    void shouldGetInventory() {
        storeClient.getInventory()
                .then()
                .statusCode(200)
                .body("$", aMapWithSize(greaterThan(0)));

    }

}
