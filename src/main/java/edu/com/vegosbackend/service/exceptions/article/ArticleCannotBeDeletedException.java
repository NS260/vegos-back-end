package edu.com.vegosbackend.service.exceptions.article;

public class ArticleCannotBeDeletedException extends RuntimeException {
    public ArticleCannotBeDeletedException(Long id) {
        super("Article cannot be deleted. Article id: " + id);
    }
}