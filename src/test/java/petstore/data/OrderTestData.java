package petstore.data;

import java.time.Instant;

import petstore.config.TestConfig;
import petstore.model.Order;

public class OrderTestData extends TestConfig {

    public static Order defaultOrder(long petId) {
        return new Order(
                petId,
                1,
                Instant.now().toString(),
                "placed",
                true
        );
    }

    public static Order orderWithNegativeQuantity() {
        return new Order(
                1,
                -10,
                Instant.now().toString(),
                "placed",
                true
        );
    }

    public static Order invalidOrderWithoutPetId() {
        Order order = new Order();
        order.quantity = 1;
        return order;
    }
}

