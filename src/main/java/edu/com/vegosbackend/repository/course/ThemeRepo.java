package edu.com.vegosbackend.repository.course;

import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepo extends JpaRepository<StructureTheme, Long> {
    List<StructureTheme> findAllByCourse(Course course);
}
