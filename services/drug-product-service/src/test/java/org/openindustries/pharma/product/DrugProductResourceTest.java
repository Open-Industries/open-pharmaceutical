package org.openindustries.pharma.product;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DrugProductResourceTest {

    @Test
    void createAndFetch() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"Cardiomax 50mg\",\"productType\":\"TABLET\",\"atcCode\":\"C09AA\"}")
                .when().post("/api/v1/drug-products")
                .then().statusCode(201)
                .body("status", is("REGISTERED"))
                .body("productType", is("TABLET"))
                .extract().path("id");

        given().when().get("/api/v1/drug-products/" + id)
                .then().statusCode(200).body("status", is("REGISTERED"));
    }

    @Test
    void transitionWithoutBody() {
        // Lifecycle transitions take no body and must not require a Content-Type (no 415).
        String id = given().contentType("application/json")
                .body("{\"name\":\"Cardiomax 50mg\",\"productType\":\"TABLET\",\"atcCode\":\"C09AA\"}")
                .when().post("/api/v1/drug-products")
                .then().statusCode(201)
                .extract().path("id");

        given().when().post("/api/v1/drug-products/" + id + "/approve")
                .then().statusCode(200).body("status", is("APPROVED"));
    }
}
