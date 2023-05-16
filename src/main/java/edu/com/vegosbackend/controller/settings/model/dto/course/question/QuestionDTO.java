package edu.com.vegosbackend.controller.settings.model.dto.course.question;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class QuestionDTO {
    private long id;
    private String text;
    private Student student;
    private AnswerDTO answer;
    private LocalDateTime askedDate;
    private CourseDTO course;
}
