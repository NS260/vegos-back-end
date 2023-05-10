package edu.com.vegosbackend.service.settings.exceptions.course.structure;

public class ThemesNotFoundException extends RuntimeException {
    public ThemesNotFoundException(Long current) {
        super("Themes not found. Course id: " + current);
    }
}
