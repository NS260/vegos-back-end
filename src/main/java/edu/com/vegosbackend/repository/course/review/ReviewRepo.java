package edu.com.vegosbackend.repository.course.review;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
    List<Review> findAllByCourse(Course course);
}
