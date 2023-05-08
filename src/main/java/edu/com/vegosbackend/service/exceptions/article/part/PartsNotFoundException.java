package edu.com.vegosbackend.service.exceptions.article.part;

public class PartsNotFoundException extends RuntimeException {
    public PartsNotFoundException(Long id) {
        super("Parts are not found. Article id: " + id);
    }
}
