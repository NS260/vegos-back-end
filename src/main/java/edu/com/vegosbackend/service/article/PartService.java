package edu.com.vegosbackend.service.article;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.repository.article.PartRepo;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PartService {
    private final PartRepo partRepo;
    private final Setter<Part> partSetter;
    private final GlobalClassGetter getter;

    public Optional<Part> addPartToArticleById(Part part, Long id) {
        part.setArticle(getter.getArticle(id));
        return Optional.ofNullable(Optional.of(partRepo
                        .save(part))
                .orElseThrow(() -> new BasicException(
                        part.getClass(),
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(part.getArticle().getClass(), id.toString())))));
    }

    public Optional<Part> editPartByPartIdAndArticleId(Part part, Long id, Long current) {
        return Optional.ofNullable(Optional.of(partRepo
                        .save(partSetter.setValue(getter.getPart(current, id), part)))
                .orElseThrow(() -> new BasicException(
                        part.getClass(),
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(part.getClass(), id.toString()),
                                new ExceptionModel(part.getArticle().getClass(), current.toString())))));
    }

    public void deletePartByPartIdAndArticleId(Long id, Long current) {
        partRepo.deleteById(id);
        if (getter.getArticle(current)
                .getParts()
                .stream()
                .anyMatch(val -> val.getId() == id)) {
            throw new BasicException(
                    Part.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Part.class, id.toString()),
                            new ExceptionModel(Article.class, current.toString())));
        }
    }

    public Optional<Part> getPartByPartIdAndArticleId(Long current, Long id) {
        return Optional.ofNullable(Optional.of(getter.getPart(current, id))
                .orElseThrow(() -> new BasicException(
                        Part.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Part.class, id.toString()),
                                new ExceptionModel(Article.class, current.toString())))));
    }

    public List<Part> getAllPartsByArticle(Long id) {
        return partRepo
                .findAllByArticle(Optional.of(getter.getArticle(id))
                        .orElseThrow(() -> new BasicException(
                                Part.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Article.class, id.toString())))));
    }
}
