package vlad110kg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.service.IStreetService;
import vlad110kg.service.StoryView;
import vlad110kg.web.api.map.AddressDto;

@RestController
@RequestMapping("/v1/street")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class StreetController {

    private final IStreetService streetService;

    @GetMapping("/story")
    public StoryView getStory(@RequestParam Double lat, @RequestParam Double lng) {
        return streetService.makeStory(lat, lng);
    }

    @GetMapping("/address")
    public AddressDto getAddress(@RequestParam Double lat, @RequestParam Double lng) {
        return streetService.findAddress(lat, lng);
    }
}
