package vlad110kg.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import vlad110kg.service.ForbiddenService;
import vlad110kg.service.StoryView;
import vlad110kg.web.api.map.AddressDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(1)
public class ForbiddenAddressFilter implements AddressFilter {

    private final ForbiddenService forbiddenService;

    @Override
    public Optional<StoryView> filter(AddressDto dto) {
        return forbiddenService.findByKey(dto.getCountryCode())
            .stream()
            .findAny()
            .map(forbidden -> new StoryView(
                dto.getCountry(),
                dto.getCity(),
                dto.getRoad(),
                forbidden.getValue()
            ));
    }
}
