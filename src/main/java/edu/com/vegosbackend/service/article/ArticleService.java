package edu.com.vegosbackend.service.article;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.repository.article.ArticleRepo;

import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepo articleRepo;
    private final Setter<Article> articleSetter;

    private final GlobalClassGetter getter;

    public Optional<Article> createArticle(Article article) {
        article.setPublishedDate(LocalDateTime.now());
        return Optional.ofNullable(Optional.of(articleRepo
                        .save(article))
                .orElseThrow(() -> new BasicException(
                        article.getClass(),
                        MessageType.NOT_CREATED)
                ));
    }

    public Optional<Article> getArticleById(Long id) {
        return Optional.ofNullable(getter.getArticle(id));
    }

    public List<Article> getAllArticles() {
        return Optional.of(articleRepo
                        .findAll())
                .orElseThrow(() -> new BasicException(
                        Article.class,
                        MessageType.NOT_FOUND
                ));
    }

    public Optional<Article> updateArticleById(Article article, Long id) {
        return Optional.ofNullable(Optional.of(articleRepo
                        .save(articleSetter.setValue(getter.getArticle(id), article)))
                .orElseThrow(() -> new BasicException(
                        Article.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(new ExceptionModel(article.getClass(), id.toString()))
                )));
    }

    public void deleteArticleById(Long id) {
        articleRepo.deleteById(id);
        if (articleRepo.existsById(id)) {
            throw new BasicException(
                    Article.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(new ExceptionModel(Article.class, id.toString())
                    ));
        }
    }

}
