package edu.com.vegosbackend.controller.settings.model.dto.article;

import edu.com.vegosbackend.domain.constants.article.ArticleType;
import edu.com.vegosbackend.domain.constants.course.Category;
import edu.com.vegosbackend.domain.main.article.Part;
import edu.com.vegosbackend.domain.main.user.User;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
//TODO : add Internationalization [time-unlimited]
@Component
@Data
public class ArticleDTO {
    private long id;
    private ArticleType articleType;
    private Category category;
    @Size(min = 10, message = "Article name should have at least 10 characters")
    private String name;
    private LocalDateTime publishedDate;
    @Size(min = 10, message = "User comment should have at least 10 characters")
    private String userComment;
    private String photoUrl;
    private int rate;
    private User user;
    private List<Part> parts;
}
