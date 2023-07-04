package vlad110kg.web.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import vlad110kg.AbstractApiTest;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class AdminForbiddenApiTest extends AbstractApiTest {


    static Stream<Arguments> provideForbiddenValuesParams() {
        return Stream.of(
            Arguments.of("ru", "Terrorist state"),
            Arguments.of("by", "Swamp"),
            Arguments.of("cn", "Communists dont need history"),
            Arguments.of("ir", "Terrorists")
        );
    }

    @MethodSource("provideForbiddenValuesParams")
    @ParameterizedTest
    void When_GetByKeyExistingValue_Then_ExpectForbiddenExists(String key, String expectedValue) {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/forbidden/{key}", key)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(1))
            .body("[0].key", equalTo(key))
            .body("[0].value", equalTo(expectedValue));
    }

    @Test
    void When_GetByNotExistingKey_Then_EmptyResponse() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/forbidden/{key}", "any")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(0));
    }

    @Test
    void When_PostForbidden_Then_NewCreated() {

        var body = """
            {
            "key":"key1",
            "value":"value1"
            }
            """;

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(body)
            .post("/admin/forbidden")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("key", equalTo("key1"))
            .body("value", equalTo("value1"));
    }

    @Test
    @Sql(value = "/sql/forbidden/id-1.sql")
    void When_DeleteExistingForbidden_Then_ExpectItDeleted() {

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/admin/forbidden/{key}", "gavno")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(1))
            .body("[0].key", equalTo("gavno"))
            .body("[0].value", equalTo("aga"));

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .delete("/admin/forbidden/-1")
            .then()
            .statusCode(HttpStatus.SC_OK);
    }
}