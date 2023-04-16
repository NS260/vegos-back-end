package edu.com.vegosbackend.model.addons.structure;

import edu.com.vegosbackend.model.main.course.CourseDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "course_structure")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_structure_id")
    private long courseStructureId;
    @OneToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    private CourseDetails course;
    @OneToMany(mappedBy = "courseStructure")
    private Set<StructureTheme> structureThemes;
}
