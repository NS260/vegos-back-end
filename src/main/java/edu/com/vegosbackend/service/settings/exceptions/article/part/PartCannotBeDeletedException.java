package edu.com.vegosbackend.service.settings.exceptions.article.part;

public class PartCannotBeDeletedException extends RuntimeException {
    public PartCannotBeDeletedException(Long id, Long current) {
        super("Part cannot be deleted. Part id: " + id + ", Article id: " + current);
    }
}
