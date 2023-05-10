package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.service.settings.exceptions.course.*;
import edu.com.vegosbackend.service.settings.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Data
public class CourseService {
    private final CourseRepo courseRepo;
    private final Setter<Course> courseSetter;

    public Optional<Course> createCourse(Course course) {
        course.setCreateDate(LocalDateTime.now());
        return Optional.ofNullable(Optional.of(courseRepo
                        .save(course))
                .orElseThrow(() -> new CourseCannotBeCreatedException(course)));
    }

    public Optional<Course> getCourseById(Long id) {
        return Optional.ofNullable(courseRepo
                .findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id)));
    }

    public List<Course> getAllCourses() {
        return Optional.of(courseRepo
                        .findAll())
                .orElseThrow(CoursesNotFoundException::new);
    }

    public Optional<Course> updateCourseById(Course course, Long id) {
        return Optional.ofNullable(Optional.of(courseRepo
                        .save(courseSetter
                                .setValue(courseRepo
                                                .findById(id)
                                                .orElseThrow(() -> new CourseNotFoundException(id)),
                                        course)))
                .orElseThrow(() -> new CourseCannotBeUpdatedException(course, id)));
    }

    public void deleteCourseById(Long id) {
        courseRepo.deleteById(id);
        if (courseRepo.existsById(id)) {
            throw new CourseCannotBeDeletedException(id);
        }
    }
}
