package edu.com.vegosbackend.repository.course;

import edu.com.vegosbackend.domain.main.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepo extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
}
