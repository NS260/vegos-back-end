package edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme;

public class SubThemesNotFoundException extends RuntimeException {
    public SubThemesNotFoundException(Long current, Long theme) {
        super("SubThemes not found. Course id: " + current + ", Theme id: " + theme);
    }
}
