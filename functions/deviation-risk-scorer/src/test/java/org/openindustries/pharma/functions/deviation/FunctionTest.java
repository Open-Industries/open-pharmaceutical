package org.openindustries.pharma.functions.deviation;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void healthyIsNormal() {
        given().contentType("application/json")
                .body("{\"batchId\":\"b-1\",\"deviationIndex\":3,\"excursionIndex\":2,\"complaintIndex\":1}")
                .when().post("/deviationRiskScorer")
                .then().statusCode(200).body("decision", is("NORMAL")).body("score", is(0));
    }

    @Test
    void stackedSignalsAreCritical() {
        given().contentType("application/json")
                .body("{\"batchId\":\"b-2\",\"deviationIndex\":86,\"excursionIndex\":74,\"complaintIndex\":68}")
                .when().post("/deviationRiskScorer")
                .then().statusCode(200).body("decision", is("CRITICAL")).body("score", is(100));
    }
}
