package vlad110kg.filter;

import vlad110kg.service.StoryView;
import vlad110kg.web.api.map.AddressDto;

import java.util.Optional;

public interface AddressFilter {
    Optional<StoryView> filter(AddressDto dto);
}
