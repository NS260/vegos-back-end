package edu.com.vegosbackend.controller.settings.model.dto.course.structure;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StructureSubThemeDTO {
    private long subthemeId;
    private StructureThemeDTO structureTheme;
    @Size(min = 5, message = "Subtheme name should have at least 5 characters")
    private String name;
    @DecimalMin(value = "0.01",message = "Time should be at least 1 minute")
    private float time;
}
