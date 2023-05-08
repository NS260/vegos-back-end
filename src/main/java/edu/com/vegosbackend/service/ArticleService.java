package edu.com.vegosbackend.service;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.repository.article.ArticleRepo;
import edu.com.vegosbackend.repository.article.PartRepo;
import edu.com.vegosbackend.service.exceptions.article.ArticlesCannotBeDeletedException;
import edu.com.vegosbackend.service.exceptions.article.*;
import edu.com.vegosbackend.service.exceptions.article.part.*;
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
    private final PartRepo partRepo;

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
                        .save(setValueForArticle(articleRepo
                                        .findById(id)
                                        .orElseThrow(() -> new ArticleNotFoundException(id)),
                                article)))
                .orElseThrow(() -> new ArticleCannotBeUpdatedException(article, id)));
    }

    private Article setValueForArticle(Article current, Article loaded) {
        loaded.setId(current.getId());
        loaded.setPublishedDate(current.getPublishedDate());
        loaded.setUser(current.getUser());
        loaded.setCategory(current.getCategory());
        loaded.setArticleType(current.getArticleType());
        if (loaded.getParts() == null) {
            loaded.setParts(current.getParts());
        }
        if (loaded.getName() == null) {
            loaded.setName(current.getName());
        }
        if (loaded.getPhotoUrl() == null) {
            loaded.setPhotoUrl(current.getPhotoUrl());
        }
        if (loaded.getRate() == 0) {
            loaded.setRate(current.getRate());
        }
        if (loaded.getUserComment() == null) {
            loaded.setUserComment(current.getUserComment());
        }
        return loaded;
    }

    public void deleteArticleById(Long id) {
        articleRepo.deleteById(id);
        if (articleRepo.existsById(id)) {
            throw new ArticleCannotBeDeletedException(id);
        }
    }

    public Optional<Part> addPartToArticleById(Part part, Long id) {
        Article article = articleRepo
                .findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
        part.setArticle(article);
        return Optional.ofNullable(Optional.of(partRepo
                        .save(part))
                .orElseThrow(() -> new PartCannotBeAddedException(part, id)));
    }

    public Optional<Part> editPartByPartIdAndArticleId(Part part, Long id, Long current) {
        return Optional.ofNullable(Optional.of(partRepo
                        .save(setValueForPart(articleRepo
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

    private Part setValueForPart(Part current, Part loaded) {
        loaded.setId(current.getId());
        loaded.setArticle(current.getArticle());
        if (loaded.getText() == null) {
            loaded.setText(current.getText());
        }
        if (loaded.getHeader() == null) {
            loaded.setHeader(current.getHeader());
        }
        return loaded;
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
        return partRepo.findAllByArticle(articleRepo
                .findById(id)
                .orElseThrow(() -> new PartsNotFoundException(id)));
    }

    public void clear() {
        articleRepo.deleteAll();
        if (articleRepo.findAll().isEmpty()) {
            throw new ArticlesCannotBeDeletedException();
        }
    }
}
