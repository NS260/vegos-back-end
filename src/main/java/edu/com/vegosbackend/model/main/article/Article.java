package edu.com.vegosbackend.model.main.article;

import edu.com.vegosbackend.model.constants.article.ArticleType;
import edu.com.vegosbackend.model.constants.course.Category;
import edu.com.vegosbackend.model.main.user.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private long articleId;
    @Enumerated
    @Column(columnDefinition = "smallint", nullable = false)
    private ArticleType articleType;
    @Enumerated
    @Column(columnDefinition = "smallint", nullable = false)
    private Category category;
    @Column(name = "name")
    private String name;
    @Column(columnDefinition = "timestamp")
    private LocalDateTime publishedDate;
    @Column(name = "user_comment")
    private String userComment;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "rate")
    private int rate;
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id", nullable = false, columnDefinition = "bigint")
    private UserDetails user;
    @OneToMany(mappedBy = "article")
    private Set<Part> parts;
}
