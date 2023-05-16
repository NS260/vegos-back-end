package edu.com.vegosbackend.controller.settings.model.dto.course.review;

import edu.com.vegosbackend.domain.main.course.review.RateType;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RateDTO {
    private long id;
    private RateType type;
    private double value;
    private ReviewDTO review;
}
