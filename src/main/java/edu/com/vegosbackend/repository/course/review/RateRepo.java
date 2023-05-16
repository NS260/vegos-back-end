package edu.com.vegosbackend.repository.course.review;

import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.domain.main.course.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
    List<Rate> findAllByReview(Review review);
}
