package edu.com.vegosbackend.service.exceptions.article.part;

public class PartNotFoundException extends RuntimeException{
    public PartNotFoundException(Long id){
        super("Part not found! Part id: " + id);
    }
}
