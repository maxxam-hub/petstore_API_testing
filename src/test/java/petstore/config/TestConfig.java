package petstore.config;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class TestConfig {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "https://petstore.swagger.io/v2");
        RestAssured.requestSpecification = given()
            .header("api_key", "special-key");
    }
}