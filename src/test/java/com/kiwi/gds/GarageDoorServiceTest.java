package com.kiwi.gds;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GarageDoorServiceTest {

    @Inject
    StatusService statusService;

    @Test
    public void testStatusEndpoint() {
        given()
          .when().get("/garagedoor/status")
          .then()
             .statusCode(200)
             .body(containsString(statusService.getStatus().name())); // TODO its possible to do it better
    }

}