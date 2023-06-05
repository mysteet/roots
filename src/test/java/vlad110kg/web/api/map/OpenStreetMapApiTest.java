package vlad110kg.web.api.map;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import vlad110kg.AbstractApiTest;

import static org.hamcrest.Matchers.*;

class OpenStreetMapApiTest extends AbstractApiTest {

    @Test
    void When_() {
        RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/v1/street/address?lat={lat}&&lng={lng}", 50.44636213478777, 30.520266294479374)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("road", is("Khreshchatyk Street"))
            .body("city", is("Kyiv"))
            .body("postcode", is("01901"))
            .body("country_code", is("ua"));
    }

}