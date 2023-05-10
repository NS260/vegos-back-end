package edu.com.vegosbackend.repository.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<PriceDetails, Long> {
    List<PriceDetails> findAllByCourse(Course course);
}
