package edu.com.vegosbackend.service.settings.setters.course;

import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.service.settings.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class SubThemeSetter implements Setter<StructureSubTheme> {
    @Override
    public StructureSubTheme setValue(StructureSubTheme before, StructureSubTheme after) {
        after.setSubthemeId(before.getSubthemeId());
        after.setStructureTheme(before.getStructureTheme());
        if (after.getName() == null) {
            after.setName(before.getName());
        }
        if (after.getTime() <= 0F) {
            after.setTime(before.getTime());
        }
        return after;
    }
}
