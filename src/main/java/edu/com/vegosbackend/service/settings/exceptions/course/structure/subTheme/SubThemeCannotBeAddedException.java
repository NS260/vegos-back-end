package edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme;

import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;

public class SubThemeCannotBeAddedException extends RuntimeException {
    public SubThemeCannotBeAddedException(Long current, Long theme, StructureSubTheme subTheme) {
        super("SubTheme cannot be added. Course id: " + current + ", Theme id: " + theme + ", SubTheme: " + subTheme);
    }
}
