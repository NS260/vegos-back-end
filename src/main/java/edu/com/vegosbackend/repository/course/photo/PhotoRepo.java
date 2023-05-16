package edu.com.vegosbackend.repository.course.photo;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
    List<Photo> findAllByCourse(Course course);
}
