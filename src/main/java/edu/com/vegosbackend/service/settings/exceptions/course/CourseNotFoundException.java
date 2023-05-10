package edu.com.vegosbackend.service.settings.exceptions.course;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Course not found! Course id: " + id);
    }
}
