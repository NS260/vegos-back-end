package edu.com.vegosbackend.service.settings.setters.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.service.settings.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class CourseSetter implements Setter<Course> {
    @Override
    public Course setValue(Course before, Course after) {
        after.setCreateDate(before.getCreateDate());
        return after;
    }
}
