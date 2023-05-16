package edu.com.vegosbackend.service.settings.modifiers.setters.course.question;

import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import org.springframework.stereotype.Component;

@Component
public class AnswerSetter implements Setter<Answer> {
    @Override
    public Answer setValue(Answer before, Answer after) {
        after.setAnsweredDate(before.getAnsweredDate());
        after.setMentor(before.getMentor());
        after.setId(before.getId());
        if (after.getQuestion() == null) {
            after.setQuestion(before.getQuestion());
        }
        if (after.getText() == null) {
            after.setText(before.getText());
        }
        return after;
    }
}
