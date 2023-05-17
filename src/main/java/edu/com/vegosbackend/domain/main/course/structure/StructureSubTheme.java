package edu.com.vegosbackend.domain.main.course.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Subtheme should be specified")
    private StructureTheme structureTheme;
    @Column(name = "subtheme_name")
    @NotBlank(message = "Subtheme name cannot be empty")
    private String name;
    @Column(name = "time")
    private float time;
}
