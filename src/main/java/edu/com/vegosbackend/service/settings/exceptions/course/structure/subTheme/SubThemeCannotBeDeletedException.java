package edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme;

public class SubThemeCannotBeDeletedException extends RuntimeException {
    public SubThemeCannotBeDeletedException(Long current, Long theme, Long id) {
        super("SubTheme cannot be deleted. Course id: " + current + ", Theme id: " + theme + ", SubTheme id: " + id);
    }
}
