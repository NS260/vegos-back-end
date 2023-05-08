package edu.com.vegosbackend.service.exceptions.article.part;

import edu.com.vegosbackend.domain.main.article.Part;

public class PartCannotBeAddedException extends RuntimeException {
    public PartCannotBeAddedException(Part part, Long id) {
        super("Part cannot be added. Article id : " + id + ", Part: " + part);
    }
}
