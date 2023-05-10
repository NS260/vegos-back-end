package edu.com.vegosbackend.controller.settings.model.dto.course.photo;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PhotoDTO {
    private long id;
    @NotEmpty(message = "Photo url should have correct value")
    private String url;
    private CourseDTO course;
}
