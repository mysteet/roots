package vlad110kg.service;

import vlad110kg.web.api.map.AddressDto;

public interface IStreetService {

    AddressDto findAddress(Double lat, Double lng);
}
