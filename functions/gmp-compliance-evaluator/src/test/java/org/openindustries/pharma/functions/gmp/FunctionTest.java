package org.openindustries.pharma.functions.gmp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FunctionTest {

    @Test
    void compliant() {
        given().contentType("application/json")
                .body("{\"batchId\":\"b-1\",\"measuredOpenDeviations\":0,\"targetOpenDeviations\":0,\"measuredDocumentationPct\":100.0,\"targetDocumentationPct\":100.0}")
                .when().post("/gmpComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(true)).body("findings.size()", is(0));
    }

    @Test
    void breachIsReported() {
        given().contentType("application/json")
                .body("{\"batchId\":\"b-2\",\"measuredOpenDeviations\":3,\"targetOpenDeviations\":0,\"measuredDocumentationPct\":100.0,\"targetDocumentationPct\":100.0}")
                .when().post("/gmpComplianceEvaluator")
                .then().statusCode(200).body("compliant", is(false)).body("findings.size()", is(1));
    }
}
