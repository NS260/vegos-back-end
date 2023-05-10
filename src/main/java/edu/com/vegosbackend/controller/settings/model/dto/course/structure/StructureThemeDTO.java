package edu.com.vegosbackend.controller.settings.model.dto.course.structure;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StructureThemeDTO {
    private long themeId;
    private CourseDTO course;
    @Size(min = 5, message = "Theme name should have at least 5 characters")
    private String name;
}
