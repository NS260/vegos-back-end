package edu.com.vegosbackend.service.settings.exceptions.course.structure;

public class ThemeNotFoundException extends RuntimeException {
    public ThemeNotFoundException(Long id, Long current) {
        super("Theme not found. Theme id: " + id + ", Course id: " + current);
    }

    public ThemeNotFoundException(Long id) {
        super("Theme not found. Theme id: " + id);
    }
}
