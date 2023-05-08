package edu.com.vegosbackend.domain.addons.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "structure_theme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureTheme {
    @Id
    @Column(name = "theme_id")
    private long structureLineId;
    @ManyToOne
    @JoinColumn(name = "course_structure_id", nullable = false, columnDefinition = "bigint")
    private CourseStructure courseStructure;
    @OneToMany(mappedBy = "structureTheme")
    private Set<StructureSubTheme> structureSubThemes;
    @Column(name = "theme_name")
    private String name;
}