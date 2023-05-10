package edu.com.vegosbackend.service.settings.exceptions.course.structure;

import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;

public class ThemeCannotBeUpdatedException extends RuntimeException {
    public ThemeCannotBeUpdatedException(StructureTheme theme, Long id, Long current) {
        super("Theme cannot be updated. Course id: " + current + ", Theme id: " + id + ", Theme: " + theme);
    }
}
