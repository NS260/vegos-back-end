package edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme;

public class SubThemeNotFoundException extends RuntimeException {
    public SubThemeNotFoundException(Long current, Long theme, Long id) {
        super("SubTheme not found. SubTheme id: " + id + ", Theme id: " + theme + ", Course id: " + current);
    }

    public SubThemeNotFoundException(Long id) {
        super("SubTheme not found. SubTheme id: " + id);
    }
}
