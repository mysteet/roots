package vlad110kg.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vlad110kg.entity.Street;
import vlad110kg.repository.StreetRepository;
import vlad110kg.web.model.StreetRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminStreetService {

    private final StreetRepository streetRepository;

    public Street save(StreetRequest req) {
        var street = Optional.ofNullable(req.id())
            .flatMap(streetRepository::findById)
            .orElseGet(Street::new);
        street.setTitle(req.title());

        return streetRepository.save(street);
    }

    public List<Street> getAll() {
        return streetRepository.findAll();
    }

    public void delete(Long id) {
        streetRepository.deleteById(id);
    }
}
