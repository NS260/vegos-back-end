package edu.com.vegosbackend.service.settings.exceptions.course;

public class CourseCannotBeDeletedException extends RuntimeException {
    public CourseCannotBeDeletedException(Long id) {
        super("Course cannot be deleted. Course id: " + id);
    }
}
