package vlad110kg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vlad110kg.entity.Street;
import vlad110kg.service.admin.AdminStreetService;
import vlad110kg.web.model.StreetRequest;

import java.util.List;

@RestController
@RequestMapping("/admin/street")
@RequiredArgsConstructor
public class AdminStreetController {

    private final AdminStreetService adminStreetService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Street> getAll() {
        return adminStreetService.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Street save(@RequestBody StreetRequest req) {
        return adminStreetService.save(req);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id) {
        adminStreetService.delete(id);
    }
}
