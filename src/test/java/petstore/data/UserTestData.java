package petstore.data;

import java.util.List;
import java.util.UUID;

import petstore.model.User;

public class UserTestData {

    public static User defaultUser() {
        return new User(randomId(), "user_" + UUID.randomUUID(), "Ivan",
    "Ivanov", "test@test.com", "12345", "79990000000", 1);
    }

    public static User userWithoutUsername() {
        User user = defaultUser();
        user.username = null;
        return user;
    }

    public static List<User> usersList(int count) {
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> defaultUser())
                .toList();
    }

    private static long randomId() {
        return System.currentTimeMillis();
    }
}