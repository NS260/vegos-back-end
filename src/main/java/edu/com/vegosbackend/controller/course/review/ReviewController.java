package edu.com.vegosbackend.controller.course.review;

import edu.com.vegosbackend.controller.settings.model.assembler.course.review.RateModelAssembler;
import edu.com.vegosbackend.controller.settings.model.assembler.course.review.ReviewModelAssembler;
import edu.com.vegosbackend.controller.settings.model.dto.course.review.RateDTO;
import edu.com.vegosbackend.controller.settings.model.dto.course.review.ReviewDTO;
import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.domain.main.course.review.Review;
import edu.com.vegosbackend.service.course.review.ReviewService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/courses/{current}/reviews")
@Data
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper reviewMapper;
    private final ReviewModelAssembler reassembler;
    private final RateModelAssembler rassembler;

    @GetMapping
    public CollectionModel<EntityModel<ReviewDTO>> getAllReviewsByCourse(@PathVariable Long current) {
        return CollectionModel.of(
                reviewService.getAllReviewsByCourseId(current)
                        .stream()
                        .map(val -> reviewMapper.map(val, ReviewDTO.class))
                        .map(reassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ReviewController.class)
                        .getAllReviewsByCourse(current))
                        .withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ReviewDTO>> getReviewById(@PathVariable Long current, @PathVariable Long id) {
        EntityModel<ReviewDTO> entityModel = reassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .getReviewByReviewIdAndCourseId(current, id)
                                .get(), ReviewDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping
    public ResponseEntity<?> addReviewById(@PathVariable Long current, @Valid @RequestBody ReviewDTO reviewDTO) {
        EntityModel<ReviewDTO> entityModel = reassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .addReviewToCourseById(reviewMapper
                                        .map(reviewDTO, Review.class), current)
                                .get(), ReviewDTO.class)
                );
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editReviewById(@PathVariable Long current, @PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {
        EntityModel<ReviewDTO> entityModel = reassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .editReviewByReviewIdAndCourseId(reviewMapper
                                        .map(reviewDTO, Review.class), id, current)
                                .get(), ReviewDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long current, @PathVariable Long id) {
        reviewService.deleteReviewByReviewIdAndCourseId(id, current);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{review}/rates")
    public CollectionModel<EntityModel<RateDTO>> getAllRatesByCourseAndReview(@PathVariable Long current, @PathVariable Long review) {
        return CollectionModel.of(
                reviewService.getAllRatesByReviewIdAndCourseId(current, review)
                        .stream()
                        .map(val -> reviewMapper.map(val, RateDTO.class))
                        .map(rassembler::toModel)
                        .collect(Collectors.toList()),
                linkTo(methodOn(ReviewController.class)
                        .getAllRatesByCourseAndReview(current, review))
                        .withSelfRel());
    }

    @GetMapping("/{review}/rates/{id}")
    public ResponseEntity<EntityModel<RateDTO>> getRateById(@PathVariable Long current, @PathVariable Long review, @PathVariable Long id) {
        EntityModel<RateDTO> entityModel = rassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .getRateByRateIdAndReviewIdAndCourseId(current, review, id)
                                .get(), RateDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/{review}/rates")
    public ResponseEntity<?> addRateById(@PathVariable Long current, @PathVariable Long review, @Valid @RequestBody RateDTO rateDTO) {
        EntityModel<RateDTO> entityModel = rassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .addRateToCourseByReviewIdAndCourseId(current, review, reviewMapper
                                        .map(rateDTO, Rate.class))
                                .get(), RateDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{review}/rates/{id}")
    public ResponseEntity<?> editRateById(@PathVariable Long current, @PathVariable Long review, @PathVariable Long id, @Valid @RequestBody RateDTO rateDTO) {
        EntityModel<RateDTO> entityModel = rassembler
                .toModel(reviewMapper
                        .map(reviewService
                                .editRateByRateIdAndReviewIdAndCourseId(current, review, id, reviewMapper
                                        .map(rateDTO, Rate.class))
                                .get(), RateDTO.class));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{review}/rates/{id}")
    public ResponseEntity<?> deleteRateById(@PathVariable Long current, @PathVariable Long review, @PathVariable Long id) {
        reviewService.deleteRateByRateIdAndReviewIdAndCourseId(current, review, id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
