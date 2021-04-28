package com.robertkoziej.api.github.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT)
public class UserControllerIntegrationTest {

    @Test
    public void should_return_ok_status_code() {
        String login = "robertkoziej";
        given().get(ServiceMappings.GET__USERS__LOGIN, login)
                .then()
                .statusCode(200);
    }
}