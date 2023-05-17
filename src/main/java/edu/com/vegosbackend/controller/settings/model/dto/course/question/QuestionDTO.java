package edu.com.vegosbackend.controller.settings.model.dto.course.question;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class QuestionDTO {
    private long id;
    @Size(min = 10,message = "Question text should have equal or more characters than 10")
    private String text;
    private Student student;
    private AnswerDTO answer;
    private LocalDateTime askedDate;
    private CourseDTO course;
}
