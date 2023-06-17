package edu.com.vegosbackend.controller.settings.model.assembler.course.group;

import edu.com.vegosbackend.controller.course.group.ClassController;
import edu.com.vegosbackend.controller.settings.model.dto.course.group.ClassDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassModelAssembler implements RepresentationModelAssembler<ClassDTO, EntityModel<ClassDTO>> {
    @Override
    public EntityModel<ClassDTO> toModel(ClassDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClassController.class).getClassByClassIdAndCourseId(entity.getCourse().getId(), entity.getClassId())).withSelfRel(),
                linkTo(methodOn(ClassController.class).getAllClassesByCourseId(entity.getCourse().getId())).withRel("classes"));
    }
}
