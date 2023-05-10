package edu.com.vegosbackend.controller.settings.model.assembler.course;

import edu.com.vegosbackend.controller.course.CourseController;
import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseModelAssembler implements RepresentationModelAssembler<CourseDTO, EntityModel<CourseDTO>> {
    @Override
    public EntityModel<CourseDTO> toModel(CourseDTO courseDTO) {
        return EntityModel.of(courseDTO,
                linkTo(methodOn(CourseController.class).getCourseById(courseDTO.getId())).withSelfRel(),
                linkTo(methodOn(CourseController.class).getAllCourses()).withRel("courses"));
    }
}
