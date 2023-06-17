package edu.com.vegosbackend.controller.settings.model.dto.course.group;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import edu.com.vegosbackend.domain.constants.course.ClassType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
public class ClassDTO {
    private long classId;
    private CourseDTO course;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int size;
    private List<Student> studentList;
    private ClassType classType;
}
