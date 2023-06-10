package vlad110kg.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import vlad110kg.entity.Story;
import vlad110kg.entity.Street;
import vlad110kg.repository.StoryRepository;
import vlad110kg.repository.StreetRepository;
import vlad110kg.web.api.ai.AiClient;
import vlad110kg.web.api.map.AddressDto;
import vlad110kg.web.api.map.MapClient;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StreetService implements IStreetService {

    private final MapClient mapClient;
    private final AiClient aiClient;
    private final StreetRepository streetRepository;
    private final StoryRepository storyRepository;

    @Override
    @Transactional
    public StoryView makeStory(Double lat, Double lng) {
        var address = findAddress(lat, lng);
        log.info("found address {} by {}, {}", address, lat, lng);
        var street = streetRepository
            .findByTitle(address.getRoad())
            .orElseGet(() -> {
                var s = new Street();
                s.setTitle(address.getRoad());
                log.info("new street {}", s);
                return streetRepository.save(s);
            });
        if (street.getStories() != null) {
            return getStoryView(address, street);
        }

        var stories = aiClient
            .askAbout(address)
            .stream()
            .map(answer -> {
                var story = new Story();
                story.setStreetId(street.getId());
                story.setInfo(answer);
                return story;
            }).toList();
        var savedStories = storyRepository.saveAll(stories);
        street.setStories(savedStories);
        log.info("update street {}", street);
        return getStoryView(address, street);
    }

    @Override
    public AddressDto findAddress(Double lat, Double lng) {
        return mapClient.findAddressByCoordinates(lat, lng);
    }

    @NotNull
    private static StoryView getStoryView(AddressDto address, Street street) {
        return new StoryView(
            address.getCountry(),
            address.getCity(),
            address.getRoad(),
            street.getStories().get(0).getInfo()
        );
    }
}
