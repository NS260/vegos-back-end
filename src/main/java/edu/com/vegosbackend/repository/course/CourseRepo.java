package edu.com.vegosbackend.repository.course;

import edu.com.vegosbackend.domain.main.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> , QuerydslPredicateExecutor<Course> {
}
