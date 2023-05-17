package edu.com.vegosbackend.controller.settings.model.dto.course.question;

import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class AnswerDTO {
    private long id;
    @Size(min = 5,message = "Answer text should have equal or more than 5 characters")
    private String text;
    private Mentor mentor;
    private LocalDateTime answeredDate;
    private Question question;
}
