package edu.com.vegosbackend.controller.settings.model.dto.course.review;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.main.course.review.Rate;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class ReviewDTO {
    private long id;
    private Student student;
    @NotEmpty(message = "Review text cannot be empty")
    private String text;
    private List<Rate> rates;
    private CourseDTO course;
}
