package edu.com.vegosbackend.domain.main.course.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity(name = "structure_theme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureTheme {
    @Id
    @Column(name = "theme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long themeId;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Course course;
    @OneToMany(mappedBy = "structureTheme")
    private List<StructureSubTheme> structureSubThemes;
    @Column(name = "theme_name")
    private String name;
}
