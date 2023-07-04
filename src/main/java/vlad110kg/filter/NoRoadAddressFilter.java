package vlad110kg.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vlad110kg.service.StoryView;
import vlad110kg.web.api.map.AddressDto;

import java.util.Optional;

@Component
@Order(10)
public class NoRoadAddressFilter implements AddressFilter {

    @Override
    public Optional<StoryView> filter(AddressDto dto) {
        if (dto.getRoad() == null) {
            return Optional.of(new StoryView(dto.getCountry(), "No", "No", "No address"));
        }
        return Optional.empty();
    }
}
