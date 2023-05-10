package edu.com.vegosbackend.domain.main.course.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStructure {
    @OneToMany(mappedBy = "course")
    private List<StructureTheme> structureThemes;
}
