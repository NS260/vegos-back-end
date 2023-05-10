package edu.com.vegosbackend.service.settings.exceptions.article;

public class ArticlesNotFoundException extends RuntimeException{
    public ArticlesNotFoundException(){
        super("Articles are not found!");
    }
}
