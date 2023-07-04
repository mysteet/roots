package vlad110kg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vlad110kg.entity.Forbidden;
import vlad110kg.repository.ForbiddenRepository;
import vlad110kg.web.model.ForbiddenRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForbiddenService {

    private final ForbiddenRepository forbiddenRepository;

    public List<Forbidden> findByKey(String key) {
        return forbiddenRepository.findAllByKey(key);
    }

    public List<Forbidden> getAll() {
        return forbiddenRepository.findAll();
    }

    public Forbidden save(ForbiddenRequest r) {
        var forbidden = new Forbidden();
        forbidden.setKey(r.key());
        forbidden.setValue(r.value());
        return forbiddenRepository.save(forbidden);
    }

    public void deleteById(Long id) {
        forbiddenRepository.deleteById(id);
    }

}
