package edu.com.vegosbackend.service.exceptions.article;

public class ArticlesCannotBeDeletedException extends RuntimeException {
    public ArticlesCannotBeDeletedException(){
        super("Articles cannot be deleted!");
    }
}
