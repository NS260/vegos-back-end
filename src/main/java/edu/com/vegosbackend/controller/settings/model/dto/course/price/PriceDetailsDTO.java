package edu.com.vegosbackend.controller.settings.model.dto.course.price;

import edu.com.vegosbackend.controller.settings.model.dto.course.CourseDTO;
import edu.com.vegosbackend.domain.constants.course.ClassType;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PriceDetailsDTO {
    private long id;
    private ClassType classType;
    @Min(value = 1, message = "Price value should be more or equal of 1")
    private float price;
    private CourseDTO course;
}
