package edu.com.vegosbackend.service.article;

import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.repository.article.ArticleRepo;
import edu.com.vegosbackend.repository.article.PartRepo;
import edu.com.vegosbackend.service.settings.exceptions.article.ArticleNotFoundException;
import edu.com.vegosbackend.service.settings.exceptions.article.part.*;
import edu.com.vegosbackend.service.settings.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PartService {
    private final PartRepo partRepo;
    private final ArticleRepo articleRepo;
    private final Setter<Part> partSetter;

    public Optional<Part> addPartToArticleById(Part part, Long id) {
        part.setArticle(articleRepo
                .findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id)));
        return Optional.ofNullable(Optional.of(partRepo
                        .save(part))
                .orElseThrow(() -> new PartCannotBeAddedException(part, id)));
    }

    public Optional<Part> editPartByPartIdAndArticleId(Part part, Long id, Long current) {
        return Optional.ofNullable(Optional.of(partRepo
                        .save(partSetter
                                .setValue(articleRepo
                                                .findById(current)
                                                .orElseThrow(() -> new ArticleNotFoundException(current))
                                                .getParts()
                                                .stream()
                                                .filter(val -> val.getId() == id)
                                                .findFirst()
                                                .orElseThrow(() -> new PartNotFoundException(id)),
                                        part)))
                .orElseThrow(() -> new PartCannotBeUpdatedException(part, id, current)));
    }


    public void deletePartByPartIdAndArticleId(Long id, Long current) {
        partRepo.deleteById(id);
        if (articleRepo
                .findById(current)
                .orElseThrow(() -> new ArticleNotFoundException(current))
                .getParts()
                .stream()
                .anyMatch(val -> val.getId() == id)) {
            throw new PartCannotBeDeletedException(id, current);
        }
    }

    public Optional<Part> getPartByPartIdAndArticleId(Long current, Long id) {
        return Optional.of(articleRepo
                .findById(current)
                .orElseThrow(() -> new ArticleNotFoundException(current))
                .getParts()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PartNotFoundException(id)));
    }

    public List<Part> getAllPartsByArticle(Long id) {
        return partRepo
                .findAllByArticle(articleRepo
                        .findById(id)
                        .orElseThrow(() -> new PartsNotFoundException(id)));
    }
}
