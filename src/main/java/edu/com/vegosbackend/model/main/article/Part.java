package edu.com.vegosbackend.model.main.article;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "part")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private long partId;
    @Column(name = "header", nullable = false)
    private String header;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(referencedColumnName = "article_id", name = "article_id", nullable = false, columnDefinition = "bigint")
    private Article article;
}
