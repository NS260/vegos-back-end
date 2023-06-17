package edu.com.vegosbackend.service.settings.modifiers;

import edu.com.vegosbackend.domain.main.course.group.Class;
import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.course.question.Question;
import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.domain.main.course.review.Review;
import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.repository.article.ArticleRepo;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Data
@Component
public class GlobalClassGetter {
    private final CourseRepo courseRepo;
    private final ArticleRepo articleRepo;

    public Course getCourse(Long id) {
        return courseRepo
                .findById(id)
                .orElseThrow(() -> new BasicException(
                        Course.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Course.class, id.toString()))));
    }

    public Article getArticle(Long id) {
        return articleRepo
                .findById(id)
                .orElseThrow(() -> new BasicException(
                        Article.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Article.class, id.toString()))));
    }

    public Class getClass(Long current, Long id) {
        return getCourse(current)
                .getCourseDetails()
                .getClasses()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Class.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Class.class, id.toString()))));
    }

    public StructureTheme getTheme(Long current, Long id) {
        return getCourse(current)
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .filter(val -> val.getThemeId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        StructureTheme.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(StructureTheme.class, id.toString()))));
    }

    public Part getPart(Long current, Long id) {
        return getArticle(current)
                .getParts()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Part.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Part.class, id.toString()))));
    }

    public Photo getPhoto(Long current, Long id) {
        return getCourse(current)
                .getCourseDetails()
                .getPhotos()
                .stream()
                .filter(val -> val.getPhotoId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Photo.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Photo.class, id.toString()))));
    }

    public PriceDetails getPrice(Long current, Long id) {
        return getCourse(current)
                .getPriceDetails()
                .stream()
                .filter(val -> val.getPriceId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        PriceDetails.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(PriceDetails.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString()))));
    }

    public StructureSubTheme getSubTheme(Long current, Long theme, Long id) {
        return getTheme(current, theme)
                .getStructureSubThemes()
                .stream()
                .filter(val -> val.getSubthemeId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        StructureSubTheme.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(StructureSubTheme.class, id.toString()))));
    }

    public Review getReview(Long current, Long id) {
        return getCourse(current)
                .getCourseDetails()
                .getReviews()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Review.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Review.class, id.toString()))));
    }

    public Rate getRate(Long current, Long review, Long id) {
        return getReview(current, review)
                .getRates()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Rate.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Rate.class, id.toString()))));
    }

    public Question getQuestion(Long current, Long id) {
        return getCourse(current)
                .getCourseDetails()
                .getQuestion()
                .stream()
                .filter(val -> val.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BasicException(
                        Question.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Question.class, id.toString()))));
    }

    public Answer getAnswer(Long current, Long question, Long id) {
        return Optional.of(getQuestion(current, question)
                        .getAnswer())
                .orElseThrow(() -> new BasicException(
                        Answer.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Answer.class, id.toString()))));
    }
}
