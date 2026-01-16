package com.example.petstore.tests.user;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.User;

public class UserCreateTests extends TestConfig {

    private final UserClient userClient = new UserClient();

    @Test
    void shouldCreateUser() {
        User user = UserTestData.defaultUser();

        userClient.createUser(user)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotCreateUserWithoutUsername() {
        User user = UserTestData.userWithoutUsername();

        userClient.createUser(user)
                .then()
                .statusCode(200);
    }

    @Test
    void shouldCreateUsersWithList() {
        List<User> users = UserTestData.usersList(3);

        userClient.createUsersWithList(users)
                .then()
                .statusCode(200);
    }

}
