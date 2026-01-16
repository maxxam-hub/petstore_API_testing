package com.example.petstore.client;

import java.util.List;

import com.example.petstore.model.User;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient {

    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/user");
    }

    public Response createUsersWithList(List<User> users) {
        return given()
                .contentType(ContentType.JSON)
                .body(users)
                .when()
                .post("/user/createWithList");
    }

    public Response getUserByUsername(String username) {
        return given()
                .when()
                .get("/user/{username}", username);
    }

    public Response updateUser(String username, User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put("/user/{username}", username);
    }

    public Response deleteUser(String username) {
        return given()
                .when()
                .delete("/user/{username}", username);
    }

    public Response login(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login");
    }

    public Response logout() {
        return given()
                .when()
                .get("/user/logout");
    }
}