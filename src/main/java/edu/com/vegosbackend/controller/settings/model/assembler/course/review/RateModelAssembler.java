package edu.com.vegosbackend.controller.settings.model.assembler.course.review;

import edu.com.vegosbackend.controller.course.review.ReviewController;
import edu.com.vegosbackend.controller.settings.model.dto.course.review.RateDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RateModelAssembler implements RepresentationModelAssembler<RateDTO, EntityModel<RateDTO>> {
    @Override
    public EntityModel<RateDTO> toModel(RateDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ReviewController.class).getRateById(
                        entity.getReview().getCourse().getId(),
                        entity.getReview().getId(),
                        entity.getId()))
                        .withSelfRel(),
                linkTo(methodOn(ReviewController.class).getAllRatesByCourseAndReview(
                        entity.getReview().getCourse().getId(),
                        entity.getReview().getId()))
                        .withRel("rates"));
    }
}
