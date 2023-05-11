package edu.com.vegosbackend.service.settings.modifiers;

import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
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
                        List.of(new ExceptionModel(Course.class, id.toString()))
                ));
    }

    public Article getArticle(Long id) {
        return articleRepo
                .findById(id)
                .orElseThrow(() -> new BasicException(
                        Article.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(new ExceptionModel(Article.class, id.toString()))
                ));
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
                        List.of(new ExceptionModel(StructureTheme.class, id.toString()))
                ));
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
                                List.of(new ExceptionModel(Part.class, id.toString()))
                        )
                );
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
                        List.of(new ExceptionModel(Photo.class, id.toString()))
                ));
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
                                new ExceptionModel(Course.class, current.toString())
                        )
                ));
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
                        List.of(new ExceptionModel(StructureSubTheme.class, id.toString()))
                ));
    }
}
