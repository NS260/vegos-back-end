package edu.com.vegosbackend.service.settings.exceptions.course;

import edu.com.vegosbackend.domain.main.course.Course;

public class CourseCannotBeUpdatedException extends RuntimeException {
    public CourseCannotBeUpdatedException(Course course, Long id) {
        super("Course cannot be updated. Course: " + course + ", id: " + id);
    }
}
