package petstore.tests.user;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import petstore.client.UserClient;
import petstore.config.TestConfig;
import petstore.data.UserTestData;
import petstore.model.User;

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
