package edu.com.vegosbackend.repository.course.structure;

import edu.com.vegosbackend.domain.main.course.structure.StructureSubTheme;
import edu.com.vegosbackend.domain.main.course.structure.StructureTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubThemeRepo extends JpaRepository<StructureSubTheme, Long> {
    List<StructureSubTheme> findAllByStructureTheme(StructureTheme theme);
}
