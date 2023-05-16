package edu.com.vegosbackend.controller.settings.model.dto.course.question;

import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class AnswerDTO {
    private long id;
    private String text;
    private Mentor mentor;
    private LocalDateTime answeredDate;
    private Question question;
}
