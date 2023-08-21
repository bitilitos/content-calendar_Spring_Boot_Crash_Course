package pt.bitilitos.contentcalendar.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import pt.bitilitos.contentcalendar.model.Content;
import pt.bitilitos.contentcalendar.model.Status;
import pt.bitilitos.contentcalendar.model.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Keep collections in memory
@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(content -> content.id().equals(id)).findFirst();
    }

    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    public void save (Content content) {
        contentList.add(content);
    }

    public void update (Content content, Integer id) {
        delete(id);
        save(content);
    }

    public void delete (Integer id) {
        contentList.removeIf(c->c.id().equals(id));
    }

    @PostConstruct
    private void init() {
        Content content = new Content(1,
                "My first blog post",
                "my fist blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        contentList.add(content);

    }
}
