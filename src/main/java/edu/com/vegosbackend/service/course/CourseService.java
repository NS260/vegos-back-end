package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class CourseService {
    private final CourseRepo courseRepo;
    private final Setter<Course> courseSetter;
    private final GlobalClassGetter getter;

    public Optional<Course> createCourse(Course course) {
        course.setCreateDate(LocalDateTime.now());
        return Optional.ofNullable(Optional.of(courseRepo
                        .save(course))
                .orElseThrow(() -> new BasicException(
                        course.getClass(),
                        MessageType.NOT_CREATED)));
    }

    public Optional<Course> getCourseById(Long id) {
        return Optional.ofNullable(getter.getCourse(id));
    }

    public List<Course> getAllCourses() {
        return Optional.of(courseRepo
                        .findAll())
                .orElseThrow(() -> new BasicException(
                        Course.class,
                        MessageType.NOT_FOUND));
    }

    public Optional<Course> updateCourseById(Course course, Long id) {
        return Optional.ofNullable(Optional.of(courseRepo
                        .save(courseSetter.setValue(getter.getCourse(id), course)))
                .orElseThrow(() -> new BasicException(
                        Course.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(new ExceptionModel(course.getClass(), id.toString())))));
    }

    public void deleteCourseById(Long id) {
        courseRepo.deleteById(id);
        if (courseRepo.existsById(id)) {
            throw new BasicException(
                    Course.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(new ExceptionModel(Course.class, id.toString())));
        }
    }

    public List<Course> searchBySpecification(Specification<Course> specification) {
        return courseRepo.findAll(specification);
    }
}
