package vlad110kg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vlad110kg.repository.StreetRepository;
import vlad110kg.web.api.map.AddressDto;
import vlad110kg.web.api.map.MapClient;

@Service
@RequiredArgsConstructor
public class StreetService implements IStreetService {

    private final MapClient mapClient;
    private final StreetRepository streetRepository;

    @Override
    public AddressDto findAddress(Double lat, Double lng) {
        return mapClient.findAddressByCoordinates(lat, lng);
    }
}
