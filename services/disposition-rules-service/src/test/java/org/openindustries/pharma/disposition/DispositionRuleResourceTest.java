package org.openindustries.pharma.disposition;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DispositionRuleResourceTest {

    @Test
    void activateThenEvaluateAllowed() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"tablet-standard\",\"productType\":\"TABLET\",\"maxBatchSizeUnits\":100000,\"maxDeviationCount\":3,\"priority\":1}")
                .when().post("/api/v1/disposition-rules")
                .then().statusCode(201).body("status", is("DRAFT"))
                .extract().path("id");

        given().when().post("/api/v1/disposition-rules/" + id + "/activate")
                .then().statusCode(200).body("status", is("ACTIVE"));

        given().contentType("application/json")
                .body("{\"productType\":\"TABLET\",\"requiredMaxDeviationCount\":5,\"requiredMaxBatchSizeUnits\":80000}")
                .when().post("/api/v1/disposition-rules/evaluate")
                .then().statusCode(200).body("decision", is("ALLOWED"));
    }

    @Test
    void evaluateWithoutRuleIsDenied() {
        given().contentType("application/json")
                .body("{\"productType\":\"INJECTABLE\",\"requiredMaxDeviationCount\":5,\"requiredMaxBatchSizeUnits\":80000}")
                .when().post("/api/v1/disposition-rules/evaluate")
                .then().statusCode(200).body("decision", is("DENIED"));
    }
}
