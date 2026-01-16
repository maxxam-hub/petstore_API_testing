package com.example.petstore.tests.user;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.User;

public class UserUpdateTests extends TestConfig {

    private final UserClient userClient = new UserClient();

    @Test
    void shouldUpdateExistingUser() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        user.firstName = "UpdatedName";

        userClient.updateUser(user.username, user)
                .then()
                .statusCode(200);

        userClient.getUserByUsername(user.username)
                .then()
                .body("firstName", equalTo("UpdatedName"));
    }

    @Test
    void shouldNotUpdateNonExistingUser() {
        User user = UserTestData.defaultUser();

        userClient.updateUser("ghost_user", user)
                .then()
                .statusCode(200);
    }

}
