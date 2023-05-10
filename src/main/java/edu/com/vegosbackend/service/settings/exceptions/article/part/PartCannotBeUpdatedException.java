package edu.com.vegosbackend.service.settings.exceptions.article.part;

import edu.com.vegosbackend.domain.main.article.Part;

public class PartCannotBeUpdatedException extends RuntimeException {
    public PartCannotBeUpdatedException(Part part, Long id, Long current) {
        super("Part cannot be updated. Part id: " + id + ", Part: " + part + ", Article id: " + current);
    }
}
