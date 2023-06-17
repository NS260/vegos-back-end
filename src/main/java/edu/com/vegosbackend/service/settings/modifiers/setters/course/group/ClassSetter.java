package edu.com.vegosbackend.service.settings.modifiers.setters.course.group;

import edu.com.vegosbackend.domain.main.course.group.Class;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class ClassSetter implements Setter<Class> {
    @Override
    public Class setValue(Class before, Class after) {
        after.setId(before.getId());
        after.setCourse(before.getCourse());
        after.setStartDate(before.getStartDate());
        after.setEndDate(before.getEndDate());
        after.setName(before.getName());
        after.setClassType(before.getClassType());
        return after;
    }
}
