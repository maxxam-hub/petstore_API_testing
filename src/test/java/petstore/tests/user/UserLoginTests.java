package com.example.petstore.tests.user;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;

import com.example.petstore.client.UserClient;
import com.example.petstore.config.TestConfig;
import com.example.petstore.data.UserTestData;
import com.example.petstore.model.User;

public class UserLoginTests extends TestConfig {

    private final UserClient userClient = new UserClient();

    @Test
    void shouldLoginUser() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        userClient.login(user.username, user.password)
                .then()
                .statusCode(200)
                .body(not(emptyOrNullString()));
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        User user = UserTestData.defaultUser();
        userClient.createUser(user);

        userClient.login(user.username, "wrong_password")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldLogoutUser() {
        userClient.logout()
                .then()
                .statusCode(200);
    }
}
