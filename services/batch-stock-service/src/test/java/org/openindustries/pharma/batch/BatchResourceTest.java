package org.openindustries.pharma.batch;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BatchResourceTest {

    @Test
    void consumptionDrawsDown() {
        String id = given().contentType("application/json")
                .body("{\"productCode\":\"C09AA\",\"availableUnits\":50000}")
                .when().post("/api/v1/batch-stock")
                .then().statusCode(201).body("availableUnits", is(50000))
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"releaseType\":\"DISTRIBUTION\",\"units\":8000}")
                .when().post("/api/v1/batch-stock/" + id + "/releases")
                .then().statusCode(200)
                .body("remainingAfter", is(42000));
    }

    @Test
    void overdrawIsRejected() {
        String id = given().contentType("application/json")
                .body("{\"productCode\":\"C09AA\",\"availableUnits\":50000}")
                .when().post("/api/v1/batch-stock")
                .then().statusCode(201)
                .extract().path("id");

        given().contentType("application/json")
                .body("{\"releaseType\":\"DISTRIBUTION\",\"units\":90000}")
                .when().post("/api/v1/batch-stock/" + id + "/releases")
                .then().statusCode(422).body("status", is(422));

        given().when().get("/api/v1/batch-stock/" + id)
                .then().statusCode(200).body("availableUnits", is(50000));
    }
}
