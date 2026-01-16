package petstore.client;

import petstore.model.Order;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class StoreClient {

    public Response getInventory() {
        return given()
                .when()
                .get("/store/inventory");
    }

    public Response placeOrder(Order order) {
        return given()
                .contentType("application/json")
                .body(order)
                .when()
                .post("/store/order");
    }

    public Response getOrder(long orderId) {
        return given()
                .when()
                .get("/store/order/{orderId}", orderId);
    }

    public Response deleteOrder(long orderId) {
        return given()
                .when()
                .delete("/store/order/{orderId}", orderId);
    }
}