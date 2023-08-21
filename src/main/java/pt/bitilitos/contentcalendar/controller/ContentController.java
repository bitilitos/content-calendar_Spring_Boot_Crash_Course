package pt.bitilitos.contentcalendar.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pt.bitilitos.contentcalendar.model.Content;
import pt.bitilitos.contentcalendar.model.Status;
import pt.bitilitos.contentcalendar.model.Type;
import pt.bitilitos.contentcalendar.repository.ContentCollectionRepository;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;

    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    // make a request and find all the pieces of content in the system
    @GetMapping
    public List<Content> findAll() {
        System.out.println(repository.toString());
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createContent(@Valid @RequestBody Content content) {
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id) {

        if (!repository.existsById(id)) {
            throw (new ResponseStatusException(HttpStatus.NOT_FOUND, "Content with id " + id + " not found!"));
        }
        else {
            repository.update(content,id);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw (new ResponseStatusException(HttpStatus.NOT_FOUND, "Content with id " + id + " not found!"));
        }
        else {
            repository.delete(id);
        }
    }



}
