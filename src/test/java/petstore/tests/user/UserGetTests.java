package com.example.petstore.tests.user;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.User;

public class UserGetTests extends TestConfig {

    private final UserClient userClient = new UserClient();

    @Test
    void shouldGetCreatedUserByUsername() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        userClient.getUserByUsername(user.username)
                .then()
                .statusCode(200)
                .body("username", equalTo(user.username));
    }

    @Test
    void shouldReturn404ForNonExistingUser() {
        userClient.getUserByUsername("not_existing_user_123")
                .then()
                .statusCode(404);
    }

}
