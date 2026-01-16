package com.example.petstore.tests.user;

import org.junit.jupiter.api.Test;

import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.User;

public class UserDeleteTests extends TestConfig {

    private final UserClient userClient = new UserClient();

    @Test
    void shouldDeleteUser() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        userClient.deleteUser(user.username)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingUser() {
        userClient.deleteUser("ghost_user")
                .then()
                .statusCode(404);
    }

}
