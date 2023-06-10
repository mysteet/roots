package vlad110kg.web.api.ai;

import vlad110kg.web.api.map.AddressDto;

import java.util.List;

public interface AiClient {
    List<String> askAbout(AddressDto address);
}
