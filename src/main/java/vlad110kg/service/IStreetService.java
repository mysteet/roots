package vlad110kg.service;

import vlad110kg.entity.Street;
import vlad110kg.web.api.map.AddressDto;

public interface IStreetService {

    StoryView makeStory(Double lat, Double lng);

    AddressDto findAddress(Double lat, Double lng);
}
