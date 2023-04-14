package edu.com.vegosbackend.model.addons.structure;

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
    @JoinColumn(name = "course_structure_id", nullable = false)
    private CourseStructure courseStructure;
    @OneToMany(mappedBy = "structure_themes")
    private Set<StructureSubTheme> structureSubThemes;
    @Column(name = "theme_name")
    private String name;

    @Override
    public String toString() {
        return "StructureTheme{" +
                "structureSubThemes=" + structureSubThemes +
                ", name='" + name + '\'' +
                '}';
    }
}
