package edu.com.vegosbackend.controller.settings.model.assembler.course.structure;

import edu.com.vegosbackend.controller.course.structure.StructureController;
import edu.com.vegosbackend.controller.settings.model.dto.course.structure.StructureSubThemeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubThemeModelAssembler implements RepresentationModelAssembler<StructureSubThemeDTO, EntityModel<StructureSubThemeDTO>> {
    @Override
    public EntityModel<StructureSubThemeDTO> toModel(StructureSubThemeDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(StructureController.class).getSubThemeById(
                        entity.getStructureTheme().getCourse().getId(),
                        entity.getStructureTheme().getThemeId(),
                        entity.getSubthemeId())).withSelfRel(),
                linkTo(methodOn(StructureController.class).getAllSubThemesByCourseAndTheme(
                        entity.getStructureTheme().getCourse().getId(),
                        entity.getStructureTheme().getThemeId())).withRel("subthemes"));
    }
}
