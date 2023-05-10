package edu.com.vegosbackend.controller.settings.model.assembler.course;

import edu.com.vegosbackend.controller.course.StructureController;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureThemeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ThemeModelAssembler implements RepresentationModelAssembler<StructureThemeDTO, EntityModel<StructureThemeDTO>> {
    @Override
    public EntityModel<StructureThemeDTO> toModel(StructureThemeDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(StructureController.class).getThemeById(entity.getCourse().getId(), entity.getThemeId())).withSelfRel(),
                linkTo(methodOn(StructureController.class).getAllThemesByCourse(entity.getCourse().getId())).withRel("themes"));
    }
}
