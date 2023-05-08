package edu.com.vegosbackend.service.exceptions.article;

import edu.com.vegosbackend.domain.main.article.Article;

public class ArticleCannotBeCreatedException extends RuntimeException {
    public ArticleCannotBeCreatedException(Article article) {
        super("Article cannot be created. Article: " + article);
    }
}
