package edu.com.vegosbackend.service;

public class ArticlesCannotBeDeletedException extends RuntimeException {
    public ArticlesCannotBeDeletedException(){
        super("Articles cannot be deleted!");
    }
}
