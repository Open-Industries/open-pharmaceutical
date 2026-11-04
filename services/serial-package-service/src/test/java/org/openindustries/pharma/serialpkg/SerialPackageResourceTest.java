package org.openindustries.pharma.serialpkg;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SerialPackageResourceTest {

    @Test
    void createAndTransition() {
        String id = given().contentType("application/json")
                .body("{\"name\":\"Bottle 30ct\",\"packageType\":\"BOTTLE\",\"unitsPerPackage\":30}")
                .when().post("/api/v1/serial-packages")
                .then().statusCode(201)
                .body("status", is("PRINTED"))
                .body("packageType", is("BOTTLE"))
                .extract().path("id");

        given().when().post("/api/v1/serial-packages/" + id + "/verify")
                .then().statusCode(200).body("status", is("VERIFIED"));
    }
}
