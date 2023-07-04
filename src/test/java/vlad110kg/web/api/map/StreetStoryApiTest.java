package vlad110kg.web.api.map;

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
import static org.hamcrest.Matchers.is;

public class StreetStoryApiTest extends AbstractApiTest {

    @Test
    void When_GetStreetStoryFromPlaceWithoutRoad_Then_SeeAStandardMessage() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/v1/street/story?lat={lat}&&lng={lng}", 49.83498459187933, 25.642784545721096)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("country", is("Ukraine"))
            .body("city", is("No"))
            .body("street", is("No"))
            .body("story", is("No address"));
    }

    static Stream<Arguments> provideCoordinatesWithCountryAndMsg() {
        return Stream.of(
            Arguments.of(
                34.59160060490094,
                115.32387878083998,
                "China",
                "Communists dont need history"
            ),
            Arguments.of(52.72974841241596, 29.285857594763442, "Belarus", "Swamp"),
            Arguments.of(53.198053899045235, 33.61347344033185, "Russia", "Terrorist state"),
            Arguments.of(39.64725349634457, 126.54286408744922, "North Korea", "No Internet"),
            Arguments.of(31.418574272125653, 52.550320475959104, "Iran", "Terrorists"),
            Arguments.of(48.28582007526949, 25.931210317941527, "Ukraine", "The best street")
        );
    }

    @ParameterizedTest
    @MethodSource("provideCoordinatesWithCountryAndMsg")
    @Sql(value = {"/sql/street/ua_chertivtsi_1001.sql", "/sql/story/street-1001.sql"})
    @Sql(value = {"/sql/story/delete-1001.sql", "/sql/street/delete_streets.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void When_AddressProvided_Then_ExpectToSeeCorrectMessage(
        Double lat,
        Double lng,
        String country,
        String expectedMsg
    ) {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/v1/street/story?lat={lat}&&lng={lng}", lat, lng)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("country", is(country))
            .body("story", is(expectedMsg));
    }
}
