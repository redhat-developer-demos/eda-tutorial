package com.redhat.developers.eda;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AccountResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/account")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}