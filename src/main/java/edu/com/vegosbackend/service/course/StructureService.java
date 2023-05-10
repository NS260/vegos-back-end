package edu.com.vegosbackend.service.course;

import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.repository.course.CourseRepo;
import edu.com.vegosbackend.repository.course.SubThemeRepo;
import edu.com.vegosbackend.repository.course.ThemeRepo;
import edu.com.vegosbackend.service.settings.exceptions.course.CourseNotFoundException;
import edu.com.vegosbackend.service.settings.exceptions.course.structure.*;
import edu.com.vegosbackend.service.settings.exceptions.course.structure.subTheme.*;
import edu.com.vegosbackend.service.settings.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StructureService {
    private final ThemeRepo themeRepo;
    private final CourseRepo courseRepo;
    private final SubThemeRepo subThemeRepo;
    private final Setter<StructureTheme> themeSetter;
    private final Setter<StructureSubTheme> subThemeSetter;

    public List<StructureTheme> getAllThemesByCourseId(Long current) {
        return themeRepo
                .findAllByCourse(courseRepo
                        .findById(current)
                        .orElseThrow(() -> new ThemesNotFoundException(current)));
    }

    public Optional<StructureTheme> getThemeByThemeIdAndCourseId(Long current, Long id) {
        return Optional.of(courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .filter(val -> val.getThemeId() == id)
                .findFirst()
                .orElseThrow(() -> new ThemeNotFoundException(id, current)));
    }

    public Optional<StructureTheme> addThemeToCourseById(StructureTheme structureTheme, Long current) {
        structureTheme
                .setCourse(courseRepo
                        .findById(current)
                        .orElseThrow(() -> new CourseNotFoundException(current)));
        return Optional.ofNullable(Optional.of(themeRepo
                        .save(structureTheme))
                .orElseThrow(() -> new ThemeCannotBeAddedException(structureTheme, current)));
    }

    public Optional<StructureTheme> editThemeByThemeIdAndCourseId(StructureTheme theme, Long id, Long current) {
        return Optional.of(Optional.of(themeRepo
                        .save(themeSetter
                                .setValue(courseRepo
                                                .findById(current)
                                                .orElseThrow(() -> new CourseNotFoundException(current))
                                                .getCourseDetails()
                                                .getCourseStructure()
                                                .getStructureThemes()
                                                .stream()
                                                .filter(val -> val.getThemeId() == id)
                                                .findFirst()
                                                .orElseThrow(() -> new ThemeNotFoundException(id)),
                                        theme)))
                .orElseThrow(() -> new ThemeCannotBeUpdatedException(theme, id, current)));
    }

    public void deleteThemeByThemeIdAndCourseId(Long id, Long current) {
        themeRepo.deleteById(id);
        if (courseRepo.findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .anyMatch(val -> val.getThemeId() == id)) {
            throw new ThemeCannotBeDeletedException(id, current);
        }
    }

    public List<StructureSubTheme> getAllSubThemesByThemeIdAndCourseId(Long current, Long theme) {
        return subThemeRepo
                .findAllByStructureTheme(courseRepo
                        .findById(current)
                        .orElseThrow(() -> new CourseNotFoundException(current))
                        .getCourseDetails()
                        .getCourseStructure()
                        .getStructureThemes()
                        .stream()
                        .filter(val -> val.getThemeId() == theme)
                        .findFirst()
                        .orElseThrow(() -> new SubThemesNotFoundException(current, theme)));
    }

    public Optional<StructureSubTheme> getSubThemeBySubThemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id) {
        return Optional.of(courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .filter(val -> val.getThemeId() == theme)
                .findFirst()
                .orElseThrow(() -> new ThemeNotFoundException(theme))
                .getStructureSubThemes()
                .stream()
                .filter(val -> val.getSubthemeId() == id)
                .findFirst()
                .orElseThrow(() -> new SubThemeNotFoundException(current, theme, id))
        );
    }

    public Optional<StructureSubTheme> addSubThemeToCourseByThemeIdAndCourseId(Long current, Long theme, StructureSubTheme subTheme) {
        subTheme.setStructureTheme(courseRepo
                .findById(current)
                .orElseThrow(() -> new CourseNotFoundException(current))
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .filter(val -> val.getThemeId() == theme)
                .findFirst()
                .orElseThrow(() -> new ThemeNotFoundException(theme)));
        return Optional.ofNullable(Optional.of(subThemeRepo
                        .save(subTheme))
                .orElseThrow(() -> new SubThemeCannotBeAddedException(current, theme, subTheme)));
    }

    public Optional<StructureSubTheme> editSubThemeBySubThemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id, StructureSubTheme subTheme) {
        return Optional.of(Optional.of(subThemeRepo
                        .save(subThemeSetter
                                .setValue(courseRepo
                                        .findById(current)
                                        .orElseThrow(() -> new CourseNotFoundException(current))
                                        .getCourseDetails()
                                        .getCourseStructure()
                                        .getStructureThemes()
                                        .stream()
                                        .filter(val -> val.getThemeId() == theme)
                                        .findFirst()
                                        .orElseThrow(() -> new ThemeNotFoundException(theme))
                                        .getStructureSubThemes()
                                        .stream()
                                        .filter(val -> val.getSubthemeId() == id)
                                        .findFirst()
                                        .orElseThrow(() -> new SubThemeNotFoundException(id)), subTheme))))
                .orElseThrow(() -> new SubThemeCannotBeUpdatedException(current, theme, id, subTheme));
    }

    public void deleteSubthemeBySubthemeIdAndThemeIdAndCourseId(Long current, Long theme, Long id) {
        subThemeRepo.deleteById(id);
        if (courseRepo
                .findById(current)
                .orElseThrow()
                .getCourseDetails()
                .getCourseStructure()
                .getStructureThemes()
                .stream()
                .filter(val -> val.getThemeId() == theme)
                .findFirst()
                .orElseThrow()
                .getStructureSubThemes()
                .stream()
                .anyMatch(val -> val.getSubthemeId() == id)) {
            throw new SubThemeCannotBeDeletedException(current, theme, id);
        }
    }
}
