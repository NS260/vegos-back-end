package edu.com.vegosbackend.service.course.structure;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.repository.course.structure.SubThemeRepo;
import edu.com.vegosbackend.repository.course.structure.ThemeRepo;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StructureService {
    private final ThemeRepo themeRepo;
    private final SubThemeRepo subThemeRepo;
    private final Setter<StructureTheme> themeSetter;
    private final Setter<StructureSubTheme> subThemeSetter;
    private final GlobalClassGetter getter;

    public List<StructureTheme> getAllThemesByCourseId(Long current) {
        return themeRepo
                .findAllByCourse(Optional.of(getter.getCourse(current))
                        .orElseThrow(() -> new BasicException(
                                StructureTheme.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureTheme> getThemeByThemeIdAndCourseId(Long current, Long id) {
        return Optional.ofNullable(Optional.of(getter.getTheme(current, id))
                .orElseThrow(() -> new BasicException(
                        StructureTheme.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(StructureTheme.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureTheme> addThemeToCourseById(StructureTheme structureTheme, Long current) {
        structureTheme.setCourse(getter.getCourse(current));
        return Optional.ofNullable(Optional.of(themeRepo
                        .save(structureTheme))
                .orElseThrow(() -> new BasicException(
                        StructureTheme.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureTheme> editThemeByThemeIdAndCourseId(StructureTheme theme, Long id, Long current) {
        return Optional.of(Optional.of(themeRepo
                        .save(themeSetter.setValue(getter.getTheme(current, id), theme)))
                .orElseThrow(() -> new BasicException(
                        StructureTheme.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(StructureTheme.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public void deleteThemeByThemeIdAndCourseId(Long id, Long current) {
        themeRepo.deleteById(id);
        if (getter.getCourse(current)
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .anyMatch(val -> val.getThemeId() == id)) {
            throw new BasicException(
                    StructureTheme.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(StructureTheme.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }

    public List<StructureSubTheme> getAllSubThemesByThemeIdAndCourseId(Long current, Long theme) {
        return subThemeRepo
                .findAllByStructureTheme(Optional.of(getter.getTheme(current, theme))
                        .orElseThrow(() -> new BasicException(
                                StructureSubTheme.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(
                                        new ExceptionModel(StructureTheme.class, theme.toString()),
                                        new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureSubTheme> getSubThemeBySubThemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id) {
        return Optional.ofNullable(Optional.of(getter.getSubTheme(current, theme, id))
                .orElseThrow(() -> new BasicException(
                                StructureSubTheme.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(
                                        new ExceptionModel(StructureSubTheme.class, id.toString()),
                                        new ExceptionModel(StructureTheme.class, theme.toString()),
                                        new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureSubTheme> addSubThemeToCourseByThemeIdAndCourseId(Long current, Long theme, StructureSubTheme subTheme) {
        subTheme.setStructureTheme(getter.getTheme(current, theme));
        return Optional.ofNullable(Optional.of(subThemeRepo
                        .save(subTheme))
                .orElseThrow(() -> new BasicException(
                        StructureSubTheme.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(
                                new ExceptionModel(StructureTheme.class, theme.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<StructureSubTheme> editSubThemeBySubThemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id, StructureSubTheme subTheme) {
        return Optional.of(Optional.of(subThemeRepo
                        .save(subThemeSetter.setValue(getter.getSubTheme(current, theme, id), subTheme))))
                .orElseThrow(() -> new BasicException(
                        StructureSubTheme.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(StructureSubTheme.class, id.toString()),
                                new ExceptionModel(StructureTheme.class, theme.toString()),
                                new ExceptionModel(Course.class, current.toString()))));
    }

    public void deleteSubthemeBySubthemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id) {
        subThemeRepo.deleteById(id);
        if (getter.getTheme(current, theme)
                .getStructureSubThemes()
                .stream()
                .anyMatch(val -> val.getSubthemeId() == id)) {
            throw new BasicException(
                    StructureSubTheme.class,
                    ValueType.ID,
                    MessageType.NOT_FOUND,
                    List.of(
                            new ExceptionModel(StructureSubTheme.class, id.toString()),
                            new ExceptionModel(StructureTheme.class, theme.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }
}
