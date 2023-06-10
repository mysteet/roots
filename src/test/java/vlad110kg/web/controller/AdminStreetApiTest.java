package vlad110kg.web.controller;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import vlad110kg.AbstractApiTest;
import vlad110kg.ResourceTestHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class AdminStreetApiTest extends AbstractApiTest implements ResourceTestHelper {

    @Test
    @Sql(scripts = "/sql/street/any_street.sql")
    @Sql(scripts = "/sql/street/delete_streets.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void When_GetAllStreets_Then_ExpectHavingASingle() {

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/street")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(1))
            .body("[0].title", equalTo("any"));
    }

    @Test
    @Sql(scripts = "/sql/street/delete_streets.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void When_PostStreet_Then_ExpectStreetIsCreated() {

        var body = """
            {"title":"created"}
            """;

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(body)
            .post("/admin/street")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("title", equalTo("created"));
    }

    @Test
    @Sql(scripts = "/sql/street/street_999.sql")
    @Sql(scripts = "/sql/street/delete_streets.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void When_PostStreetWithExistingId_Then_ExpectStreetIsUpdated() {

        var body = """
            {"id":999,"title":"created"}
            """;

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(body)
            .post("/admin/street")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("id", equalTo(999))
            .body("title", equalTo("created"));
    }

    @Test
    @Sql(scripts = "/sql/street/street_999.sql")
    void When_DeleteStreetWithExistingId_Then_ExpectStreetIsUpdated() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/street")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(1))
            .body("[0].id", equalTo(999))
            .body("[0].title", equalTo("999"));

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .delete("/admin/street/" + 999)
            .then()
            .statusCode(HttpStatus.SC_OK);

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/street")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(0));
    }
}