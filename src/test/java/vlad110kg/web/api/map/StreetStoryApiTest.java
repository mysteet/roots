package vlad110kg.web.api.map;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import vlad110kg.AbstractApiTest;

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
}
