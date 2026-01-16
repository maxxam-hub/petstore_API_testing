package petstore.tests.user;

import org.junit.jupiter.api.Test;

import petstore.client.UserClient;
import petstore.config.TestConfig;
import petstore.data.UserTestData;
import petstore.model.User;

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
