package edu.com.vegosbackend.controller.settings.model.assembler.course.photo;

import edu.com.vegosbackend.controller.course.photo.PhotoController;
import edu.com.vegosbackend.controller.settings.model.dto.course.photo.PhotoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhotoModelAssembler implements RepresentationModelAssembler<PhotoDTO, EntityModel<PhotoDTO>> {
    @Override
    public EntityModel<PhotoDTO> toModel(PhotoDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PhotoController.class).getPhotoByPhotoIdAndCourseId(entity.getCourse().getId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(PhotoController.class).getAllPhotosByCourseId(entity.getCourse().getId())).withRel("photos"));
    }
}
