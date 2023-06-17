package edu.com.vegosbackend.service.course.question;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.repository.course.question.AnswerRepo;
import edu.com.vegosbackend.repository.course.question.QuestionRepo;
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
public class QuestionService {
    private final CourseRepo courseRepo;
    private final QuestionRepo questionRepo;
    private final AnswerRepo answerRepo;
    private final Setter<Question> questionSetter;
    private final Setter<Answer> answerSetter;
    private final GlobalClassGetter getter;

    public List<Question> getAllQuestionsByCourseId(Long current) {
        return questionRepo.findAllByCourse(Optional.of(getter.getCourse(current))
                .orElseThrow(() -> new BasicException(
                        Question.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Question> getQuestionByQuestionIdAndCourseId(Long current, Long id) {
        return Optional.ofNullable(Optional.of(getter.getQuestion(current, id))
                .orElseThrow(() -> new BasicException(
                        Question.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Question.class, id.toString()),
                                new ExceptionModel(Course.class, id.toString())
                        )
                )));
    }

    public Optional<Question> addQuestionToCourseById(Question question, Long current) {
        question.setCourse(getter.getCourse(current));
        return Optional.ofNullable(Optional.of(questionRepo
                        .save(question))
                .orElseThrow(() -> new BasicException(
                        Question.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(Course.class, current.toString()))
                )));
    }

    public Optional<Question> editQuestionByQuestionIdAndCourseId(Question question, Long id, Long current) {
        return Optional.of(Optional.of(questionRepo
                        .save(questionSetter.setValue(getter.getQuestion(current, id), question)))
                .orElseThrow(() -> new BasicException(
                        Question.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Question.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                )));
    }

    public void deleteQuestionByQuestionIdAndCourseId(Long id, Long current) {
        questionRepo.deleteById(id);
        if (getter.getCourse(current)
                .getCourseDetails()
                .getQuestion()
                .stream()
                .anyMatch(val -> val.getId() == id)) {
            throw new BasicException(
                    Question.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Question.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())
                    )
            );
        }
    }

    public Optional<Answer> getAnswerByAnswerIdAndQuestionIdAndCourseId(Long current, Long question, Long id) {
        return Optional.of(Optional.of(getter.getAnswer(current, question, id))
                .orElseThrow(() -> new BasicException(
                        Answer.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Answer.class, id.toString()),
                                new ExceptionModel(Question.class, question.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                )));
    }

    public Optional<Answer> addAnswerToCourseByQuestionIdAndCourseId(Long current, Long question, Answer answer) {
        changeAnswerState(answer, current, question);
        return Optional.of(Optional.of(getter.getQuestion(current, question).getAnswer()))
                .orElseThrow(() -> new BasicException(
                        Answer.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(
                                new ExceptionModel(Question.class, question.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                ));
    }

    public Optional<Answer> editAnswerByAnswerIdAndQuestionIdAndCourseId(Long current, Long question, Long id, Answer answer) {
        return Optional.of(Optional.of(answerRepo
                        .save(answerSetter.setValue(getter.getAnswer(current, question, id), answer))))
                .orElseThrow(() -> new BasicException(
                        Answer.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Answer.class, id.toString()),
                                new ExceptionModel(Question.class, question.toString()),
                                new ExceptionModel(Course.class, current.toString())
                        )
                ));
    }

    public void deleteAnswerByAnswerIdAndQuestionIdAndCourseId(Long current, Long question, Long id) {
        changeAnswerState(null, current, question);
        answerRepo.deleteById(id);
        if (answerRepo.existsById(id)) {
            throw new BasicException(
                    Answer.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Answer.class, id.toString()),
                            new ExceptionModel(Question.class, question.toString()),
                            new ExceptionModel(Course.class, current.toString())
                    )
            );
        }
    }

    private void changeAnswerState(Answer value, Long current, Long question) {
        Question test = getQuestionByQuestionIdAndCourseId(current, question).get();
        test.setAnswer(value);
        questionRepo.save(test);
    }
}
