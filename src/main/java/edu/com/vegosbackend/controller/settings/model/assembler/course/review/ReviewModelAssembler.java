package edu.com.vegosbackend.controller.settings.model.assembler.course.review;

import edu.com.vegosbackend.controller.course.review.ReviewController;
import edu.com.vegosbackend.controller.settings.model.dto.course.review.ReviewDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<ReviewDTO, EntityModel<ReviewDTO>> {
    @Override
    public EntityModel<ReviewDTO> toModel(ReviewDTO entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ReviewController.class).getReviewById(entity.getCourse().getId(), entity.getId())).withSelfRel(),
                linkTo(methodOn(ReviewController.class).getAllReviewsByCourse(entity.getCourse().getId())).withRel("reviews"));
    }
}
