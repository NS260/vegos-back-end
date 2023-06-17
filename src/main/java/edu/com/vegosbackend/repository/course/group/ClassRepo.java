package edu.com.vegosbackend.repository.course.group;

import edu.com.vegosbackend.domain.main.course.group.Class;
import edu.com.vegosbackend.domain.main.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<Class,Long> {
    List<Class> findAllByCourse(Course course);
}
