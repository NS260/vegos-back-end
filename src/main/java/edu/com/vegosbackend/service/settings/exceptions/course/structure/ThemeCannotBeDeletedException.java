package edu.com.vegosbackend.service.settings.exceptions.course.structure;

public class ThemeCannotBeDeletedException extends RuntimeException {
    public ThemeCannotBeDeletedException(Long id, Long current) {
        super("Theme cannot be deleted. Theme id: " + id + ", Course id: " + current);
    }
}
