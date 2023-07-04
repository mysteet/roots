package vlad110kg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.entity.Forbidden;
import vlad110kg.service.ForbiddenService;
import vlad110kg.web.model.ForbiddenRequest;

import java.util.List;

@RestController
@RequestMapping("/admin/forbidden")
@RequiredArgsConstructor
public class AdminForbiddenController {

    private final ForbiddenService forbiddenService;

    @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Forbidden> findByKey(@PathVariable String key) {
        return forbiddenService.findByKey(key);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Forbidden> findAll() {
        return forbiddenService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        forbiddenService.deleteById(id);
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Forbidden save(@RequestBody ForbiddenRequest r) {
        return forbiddenService.save(r);
    }
}
