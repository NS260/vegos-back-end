package edu.com.vegosbackend.service.settings.exceptions.course;

import edu.com.vegosbackend.domain.main.course.Course;

public class CourseCannotBeCreatedException extends RuntimeException {
    public CourseCannotBeCreatedException(Course course) {
        super("Course cannot be created: " + course);
    }
}
