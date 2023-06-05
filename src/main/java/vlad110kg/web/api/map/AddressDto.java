package vlad110kg.web.api.map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
    private String road;
    private String city;
    private String country;
    private String postcode;
    @JsonProperty("country_code")
    private String countryCode;
}
