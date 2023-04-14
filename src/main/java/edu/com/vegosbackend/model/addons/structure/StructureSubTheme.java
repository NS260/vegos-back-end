package edu.com.vegosbackend.model.addons.structure;

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
    @Column(name = "sub_theme_id")
    private long structureSubLineId;
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private StructureTheme structureTheme;
    @Column(name = "subtheme_name")
    private String name;
    @Column(name = "time")
    private float time;

    @Override
    public String toString() {
        return "StructureSubTheme{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
