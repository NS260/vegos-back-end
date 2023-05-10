package edu.com.vegosbackend.mapper.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureSubThemeDTO;
import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class SubThemeMapper implements CustomMapper<StructureSubTheme, StructureSubThemeDTO> {
    private final ModelMapper mapper;

    @Override
    public StructureSubTheme convertToEntity(StructureSubThemeDTO value) {
        return mapper.map(value, StructureSubTheme.class);
    }

    @Override
    public StructureSubThemeDTO convertToDTO(StructureSubTheme value) {
        return mapper.map(value, StructureSubThemeDTO.class);
    }
}
