package edu.com.vegosbackend.domain.main.course.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "structure_subtheme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructureSubTheme {
    @Id
    @Column(name = "subtheme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subthemeId;
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private StructureTheme structureTheme;
    @Column(name = "subtheme_name")
    private String name;
    @Column(name = "time")
    private float time;
}
