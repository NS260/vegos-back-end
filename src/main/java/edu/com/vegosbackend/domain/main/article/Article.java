package edu.com.vegosbackend.domain.main.article;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.vegosbackend.domain.constants.article.ArticleType;
import edu.com.vegosbackend.domain.constants.course.Category;
import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//TODO : add Internationalization [time-unlimited]
@Entity(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Enumerated
    @Column(columnDefinition = "smallint", nullable = false)
    @NotNull(message = "Article Type is mandatory")
    private ArticleType articleType;
    @Enumerated
    @Column(name = "category_id", columnDefinition = "smallint")
    @NotNull(message = "Category is mandatory")
    private Category category;
    @Column(name = "name")
    @NotBlank(message = "Article name is mandatory")
    private String name;
    @Column(columnDefinition = "timestamp")
    @NotNull(message = "Published Date is mandatory")
    private LocalDateTime publishedDate;
    @Column(name = "user_comment")
    @NotBlank(message = "User comment is mandatory")
    private String userComment;
    @Column(name = "photo_url")
    @NotEmpty(message = "Article photo url should be specified")
    private String photoUrl;
    @Column(name = "rate")
    @NotNull(message = "Rate should be specified")
    private Integer rate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false, columnDefinition = "bigint")
    @NotNull(message = "User should be specified")
    private User user;
    @OneToMany(mappedBy = "article", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Part> parts;

    @JsonManagedReference
    public List<Part> getParts() {
        return parts;
    }
}
