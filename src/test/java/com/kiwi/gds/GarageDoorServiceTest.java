package com.kiwi.gds;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GarageDoorServiceTest {

    @Test
    @Order(1)
    public void testStatusEndpoint() {
        given()
            .when().get("/garagedoor/status")
            .then()
                .statusCode(200)
                .body(containsString(StatusType.CLOSED.name())); // TODO its possible to do it better
    }

    @Test
    @Order(2)
    public void testOpen_ReturnedStatus() {
        given()
            .when().get("/garagedoor/open")
            .then()
                .statusCode(200)
                .body(containsString(StatusType.OPENING.name()));
    }

}