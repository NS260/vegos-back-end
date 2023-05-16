package edu.com.vegosbackend.service.settings.modifiers.setters.course.question;

import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class QuestionSetter implements Setter<Question> {
    @Override
    public Question setValue(Question before, Question after) {
        after.setId(before.getId());
        after.setAskedDate(before.getAskedDate());
        after.setStudent(before.getStudent());
        if (after.getAnswer() == null) {
            after.setAnswer(before.getAnswer());
        }
        if (after.getText() == null) {
            after.setText(before.getText());
        }
        return after;
    }
}
