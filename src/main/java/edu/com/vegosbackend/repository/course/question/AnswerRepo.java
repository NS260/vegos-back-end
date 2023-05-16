package edu.com.vegosbackend.repository.course.question;

import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.course.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Answer findByQuestion(Question question);
}
