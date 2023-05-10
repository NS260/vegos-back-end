package edu.com.vegosbackend.mapper.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureThemeDTO;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class ThemeMapper implements CustomMapper<StructureTheme, StructureThemeDTO> {
    private final ModelMapper mapper;

    @Override
    public StructureTheme convertToEntity(StructureThemeDTO value) {
        return mapper.map(value, StructureTheme.class);
    }

    @Override
    public StructureThemeDTO convertToDTO(StructureTheme value) {
        return mapper.map(value, StructureThemeDTO.class);
    }
}
