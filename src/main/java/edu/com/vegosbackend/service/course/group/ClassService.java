package edu.com.vegosbackend.service.course.group;

import edu.com.vegosbackend.domain.main.course.group.Class;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import edu.com.vegosbackend.repository.course.group.ClassRepo;
import edu.com.vegosbackend.repository.users.StudentRepo;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ClassService {
    private final ClassRepo classRepo;
    private final Setter<Class> classSetter;
    private final GlobalClassGetter getter;
    private final StudentRepo studentRepo;

    public Optional<Class> addClassToCourseById(Class group, Long id) {
        group.setCourse(getter.getCourse(id));
        return Optional.ofNullable(Optional.of(classRepo
                        .save(group))
                .orElseThrow(() -> new BasicException(
                        Class.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(Course.class, id.toString())))));
    }

    public Optional<Class> editClassByClassIdAndCourseId(Class group, Long id, Long current) {
        return Optional.of(Optional.of(classRepo.save(classSetter.setValue(getter.getClass(current, id), group)))
                .orElseThrow(() -> new BasicException(
                        Class.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Class.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public void deleteClassByClassIdAndCourseId(Long id, Long current) {
        classRepo.deleteById(id);
        if (classRepo.existsById(id)) {
            throw new BasicException(
                    Class.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Class.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }

    public Optional<Class> getClassByClassIdAndCourseId(Long id, Long current) {
        return Optional.of(Optional.of(getter.getClass(current, id))
                .orElseThrow(() -> new BasicException(
                        Class.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Class.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public List<Class> getAllClassesByCourseId(Long id) {
        return classRepo
                .findAllByCourse(Optional.of(getter.getCourse(id))
                        .orElseThrow(() -> new BasicException(
                                Class.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Class.class, id.toString())))));
    }

    public Optional<Student> addStudentToClass(Student student, Long id) {
        return Optional.ofNullable(classRepo.findById(id).map(group -> {
            Student newStudent = studentRepo
                    .findById(student.getId())
                    .orElseThrow(() -> new BasicException(
                            Student.class,
                            ValueType.ID,
                            MessageType.NOT_FOUND,
                            List.of(new ExceptionModel(Student.class, student.getId().toString()))));
            group.addStudent(newStudent);
            classRepo.save(group);
            return newStudent;
        }).orElseThrow(() -> new BasicException(
                Student.class,
                ValueType.ID,
                MessageType.NOT_ADDED,
                List.of(
                        new ExceptionModel(Class.class, id.toString()),
                        new ExceptionModel(Student.class, student.getId().toString())))));
    }

    public List<Student> getAllStudentsByClass(Long id) {
        if (!classRepo.existsById(id)) {
            throw new BasicException(
                    Class.class,
                    ValueType.ID,
                    MessageType.NOT_FOUND,
                    List.of(new ExceptionModel(Class.class, id.toString())));
        }
        return studentRepo.findStudentsByClassesId(id);
    }
}
