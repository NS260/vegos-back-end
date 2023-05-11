package edu.com.vegosbackend.service.settings.modifiers.setters.course;

import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class ThemeSetter implements Setter<StructureTheme> {
    @Override
    public StructureTheme setValue(StructureTheme before, StructureTheme after) {
        after.setCourse(before.getCourse());
        after.setThemeId(before.getThemeId());
        if (after.getName() == null) {
            after.setName(before.getName());
        }
        return after;
    }
}
