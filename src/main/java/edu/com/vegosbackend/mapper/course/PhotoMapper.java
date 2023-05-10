package edu.com.vegosbackend.mapper.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.photo.PhotoDTO;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.mapper.CustomMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class PhotoMapper implements CustomMapper<Photo, PhotoDTO> {
    private final ModelMapper mapper;

    @Override
    public Photo convertToEntity(PhotoDTO value) {
        return mapper.map(value, Photo.class);
    }

    @Override
    public PhotoDTO convertToDTO(Photo value) {
        return mapper.map(value, PhotoDTO.class);
    }
}
