package edu.com.vegosbackend.service.article;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.repository.article.ArticleRepo;
import edu.com.vegosbackend.service.settings.exceptions.article.*;

import edu.com.vegosbackend.service.settings.setters.Setter;
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

    public Optional<Article> createArticle(Article article) {
        article.setPublishedDate(LocalDateTime.now());
        return Optional.ofNullable(Optional.of(articleRepo
                        .save(article))
                .orElseThrow(() -> new ArticleCannotBeCreatedException(article)));
    }

    public Optional<Article> getArticleById(Long id) {
        return Optional.ofNullable(articleRepo
                .findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id)));
    }

    public List<Article> getAllArticles() {
        return Optional.of(articleRepo
                        .findAll())
                .orElseThrow(ArticlesNotFoundException::new);
    }

    public Optional<Article> updateArticleById(Article article, Long id) {
        return Optional.ofNullable(Optional.of(articleRepo
                        .save(articleSetter
                                .setValue(articleRepo
                                                .findById(id)
                                                .orElseThrow(() -> new ArticleNotFoundException(id)),
                                        article)))
                .orElseThrow(() -> new ArticleCannotBeUpdatedException(article, id)));
    }

    public void deleteArticleById(Long id) {
        articleRepo.deleteById(id);
        if (articleRepo.existsById(id)) {
            throw new ArticleCannotBeDeletedException(id);
        }
    }

    public void clear() {
        articleRepo.deleteAll();
        if (articleRepo.findAll().isEmpty()) {
            throw new ArticlesCannotBeDeletedException();
        }
    }
}
