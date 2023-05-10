package edu.com.vegosbackend.service.settings.exceptions.course.structure;

import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;

public class ThemeCannotBeAddedException extends RuntimeException {
    public ThemeCannotBeAddedException(StructureTheme structureTheme, Long current) {
        super("Theme cannot be added. Course id: " + current + ", Theme: " + structureTheme);
    }
}
