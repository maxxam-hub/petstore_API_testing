package petstore.tests.user;

import java.util.List;

import org.junit.jupiter.api.Test;

import petstore.client.UserClient;
import petstore.config.TestConfig;
import petstore.data.UserTestData;
import petstore.model.User;

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
