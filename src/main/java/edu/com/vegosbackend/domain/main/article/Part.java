package edu.com.vegosbackend.domain.main.article;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO : add Internationalization [time-unlimited]
@Entity(name = "parts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "header", nullable = false)
    @NotBlank(message = "Header is mandatory")
    private String header;
    @Column(name = "text")
    @NotBlank(message = "Text is mandatory")
    private String text;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "article_id", nullable = false, columnDefinition = "bigint")
    @NotNull(message = "Article is mandatory")
    private Article article;

    @JsonBackReference
    public Article getArticle() {
        return article;
    }
}
