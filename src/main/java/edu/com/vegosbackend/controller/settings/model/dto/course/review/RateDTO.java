package edu.com.vegosbackend.controller.settings.model.dto.course.review;

import edu.com.vegosbackend.domain.main.course.review.RateType;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RateDTO {
    private long id;
    private RateType type;
    @Min(value = 1, message = "Rate cannot be lesser than 1")
    private double value;
    private ReviewDTO review;
}
