package edu.com.vegosbackend.service.settings.modifiers.setters.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class CourseSetter implements Setter<Course> {
    @Override
    public Course setValue(Course before, Course after) {
        after.setId(before.getId());
        after.setCreateDate(before.getCreateDate());
        after.setMentor(before.getMentor());
        return after;
    }
}
