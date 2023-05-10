package edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme;

import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;

public class SubThemeCannotBeUpdatedException extends RuntimeException {
    public SubThemeCannotBeUpdatedException(Long current, Long theme, Long id, StructureSubTheme subTheme) {
        super("SubTheme cannot be updated. Course id: " + current + ", Theme id: " + theme + ", SubTheme id: " + id + ", Subtheme: " + subTheme);
    }
}
