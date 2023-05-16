package edu.com.vegosbackend.controller.settings.model.assembler.course.price;

import edu.com.vegosbackend.controller.course.price.PriceController;
import edu.com.vegosbackend.controller.settings.model.dto.course.price.PriceDetailsDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PriceModelAssembler implements RepresentationModelAssembler<PriceDetailsDTO, EntityModel<PriceDetailsDTO>> {
    @Override
    public EntityModel<PriceDetailsDTO> toModel(PriceDetailsDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(PriceController.class).getPriceById(entity.getCourse().getId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(PriceController.class).getAllPricesByCourse(entity.getCourse().getId())).withRel("prices"));
    }
}
