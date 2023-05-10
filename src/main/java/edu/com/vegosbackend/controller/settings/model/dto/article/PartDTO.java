package edu.com.vegosbackend.controller.settings.model.dto.article;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

//TODO : add Internationalization [time-unlimited]
@Component
@Data
public class PartDTO {
    private long id;
    @Size(min = 5, message = "Header should have at least 5 characters")
    private String header;
    @Size(min = 15, message = "Text area should have at least 15 characters")
    private String text;

    private ArticleDTO article;
}
