package edu.com.vegosbackend.service.settings.exceptions.article;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("Article not found. Article id: " + id);
    }
}
