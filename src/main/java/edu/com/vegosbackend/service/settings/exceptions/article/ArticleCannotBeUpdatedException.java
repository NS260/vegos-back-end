package edu.com.vegosbackend.service.settings.exceptions.article;

import edu.com.vegosbackend.domain.main.article.Article;

public class ArticleCannotBeUpdatedException extends RuntimeException {
    public ArticleCannotBeUpdatedException(Article article, Long id) {
        super("Article cannot be updated. Article id: " + id + ", Article: " + article);
    }
}
