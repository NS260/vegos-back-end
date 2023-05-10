package edu.com.vegosbackend.controller.settings.model.dto.course.structure;

import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class StructureDTO {
    private List<StructureTheme> structureThemes;
}
