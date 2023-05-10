package edu.com.vegosbackend.mapper.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.price.PriceDetailsDTO;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class PriceMapper implements CustomMapper<PriceDetails, PriceDetailsDTO> {
    private final ModelMapper mapper;

    @Override
    public PriceDetails convertToEntity(PriceDetailsDTO value) {
        return mapper.map(value, PriceDetails.class);
    }

    @Override
    public PriceDetailsDTO convertToDTO(PriceDetails value) {
        return mapper.map(value, PriceDetailsDTO.class);
    }
}
