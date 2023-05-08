package edu.com.vegosbackend.mapper.article;

import edu.com.vegosbackend.controller.model.dto.PartDTO;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class PartMapper implements CustomMapper<Part, PartDTO> {
    private final ModelMapper mapper;

    @Override
    public Part convertToEntity(PartDTO value) {
        return mapper.map(value, Part.class);
    }

    @Override
    public PartDTO convertToDTO(Part value) {
        return mapper.map(value, PartDTO.class);
    }
}
