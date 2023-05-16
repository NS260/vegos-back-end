package edu.com.vegosbackend.service.course.review;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.domain.main.course.review.Review;
import edu.com.vegosbackend.repository.course.review.RateRepo;
import edu.com.vegosbackend.repository.course.review.ReviewRepo;
import edu.com.vegosbackend.service.settings.exceptions.BasicException;
import edu.com.vegosbackend.service.settings.exceptions.model.ExceptionModel;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.MessageType;
import edu.com.vegosbackend.service.settings.exceptions.model.constants.ValueType;
import edu.com.vegosbackend.service.settings.modifiers.GlobalClassGetter;
import edu.com.vegosbackend.service.settings.modifiers.setters.Setter;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class ReviewService {
    private final ReviewRepo reviewRepo;
    private final RateRepo rateRepo;
    private final Setter<Review> reviewSetter;
    private final Setter<Rate> rateSetter;
    private final GlobalClassGetter getter;

    public List<Review> getAllReviewsByCourseId(Long current) {
        return reviewRepo
                .findAllByCourse(Optional.of(getter.getCourse(current))
                        .orElseThrow(() -> new BasicException(
                                Review.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Review> getReviewByReviewIdAndCourseId(Long current, Long id) {
        return Optional.ofNullable(Optional.of(getter.getReview(current, id))
                .orElseThrow(() -> new BasicException(
                        Review.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Review.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Review> addReviewToCourseById(Review review, Long current) {
        review.setCourse(getter.getCourse(current));
        return Optional.ofNullable(Optional.of(reviewRepo
                        .save(review))
                .orElseThrow(() -> new BasicException(
                        Review.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Review> editReviewByReviewIdAndCourseId(Review review, Long id, Long current) {
        return Optional.of(Optional.of(reviewRepo
                        .save(reviewSetter.setValue(getter.getReview(current, id), review)))
                .orElseThrow(() -> new BasicException(
                        Review.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Review.class, id.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public void deleteReviewByReviewIdAndCourseId(Long id, Long current) {
        reviewRepo.deleteById(id);
        if (getter.getCourse(current)
                .getCourseDetails()
                .getReviews()
                .stream()
                .anyMatch(val -> val.getId() == id)) {
            throw new BasicException(
                    Review.class,
                    ValueType.ID,
                    MessageType.NOT_DELETED,
                    List.of(
                            new ExceptionModel(Review.class, id.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }

    public List<Rate> getAllRatesByReviewIdAndCourseId(Long current, Long review) {
        return rateRepo
                .findAllByReview(Optional.of(getter.getReview(current, review))
                        .orElseThrow(() -> new BasicException(
                                Rate.class,
                                ValueType.ID,
                                MessageType.NOT_FOUND,
                                List.of(
                                        new ExceptionModel(Review.class, review.toString()),
                                        new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Rate> getRateByRateIdAndReviewIdAndCourseId(Long current, Long review, Long id) {
        return Optional.ofNullable(Optional.of(getter.getRate(current, review, id))
                .orElseThrow(() -> new BasicException(
                        Rate.class,
                        ValueType.ID,
                        MessageType.NOT_FOUND,
                        List.of(
                                new ExceptionModel(Rate.class, id.toString()),
                                new ExceptionModel(Review.class, review.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Rate> addRateToCourseByReviewIdAndCourseId(Long current, Long review, Rate rate) {
        rate.setReview(getter.getReview(current, review));
        return Optional.ofNullable(Optional.of(rateRepo
                        .save(rate))
                .orElseThrow(() -> new BasicException(
                        Rate.class,
                        ValueType.ID,
                        MessageType.NOT_ADDED,
                        List.of(
                                new ExceptionModel(Review.class, review.toString()),
                                new ExceptionModel(Course.class, current.toString())))));
    }

    public Optional<Rate> editRateByRateIdAndReviewIdAndCourseId(Long current, Long review, Long id, Rate rate) {
        return Optional.of(Optional.of(rateRepo
                        .save(rateSetter.setValue(getter.getRate(current, review, id), rate))))
                .orElseThrow(() -> new BasicException(
                        Rate.class,
                        ValueType.ID,
                        MessageType.NOT_UPDATED,
                        List.of(
                                new ExceptionModel(Rate.class, id.toString()),
                                new ExceptionModel(Review.class, review.toString()),
                                new ExceptionModel(Course.class, current.toString()))));
    }

    public void deleteRateByRateIdAndReviewIdAndCourseId(Long current, Long review, Long id) {
        rateRepo.deleteById(id);
        if (getter.getReview(current, review)
                .getRates()
                .stream()
                .anyMatch(val -> val.getId() == id)) {
            throw new BasicException(
                    Rate.class,
                    ValueType.ID,
                    MessageType.NOT_FOUND,
                    List.of(
                            new ExceptionModel(Rate.class, id.toString()),
                            new ExceptionModel(Review.class, review.toString()),
                            new ExceptionModel(Course.class, current.toString())));
        }
    }
}
