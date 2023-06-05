package vlad110kg.web.api.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OpenStreetMapClient implements MapClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper om;

    @Value("${client.address.url}")
    private String addressUrl;

    @Override
    public AddressDto findAddressByCoordinates(Double lat, Double lng) {
        // Build the URL
        var b = UriComponentsBuilder.fromHttpUrl(addressUrl)
            .queryParam("format", "json")
            .queryParam("lat", lat)
            .queryParam("lon", lng)
            .queryParam("zoom", 18)
            .queryParam("addressdetails", 1)
            .queryParam("accept-language", "en");

        // Send the GET request
        var response = restTemplate.getForEntity(b.toUriString(), String.class);

        try {
            // As the 'address' is a nested object in the response, we need to get it first
            var root = om.readTree(response.getBody());
            var addressNode = root.get("address");

            // Now we can convert the 'address' object to AddressDto
            return om.treeToValue(addressNode, AddressDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
    }
}
