package edu.com.vegosbackend.controller.settings.model.dto.course;

import edu.com.vegosbackend.controller.settings.model.dto.course.details.CourseDetailsDTO;
import edu.com.vegosbackend.controller.settings.model.dto.course.details.CourseFeaturesDTO;
import edu.com.vegosbackend.domain.constants.global.LanguageName;
import edu.com.vegosbackend.domain.constants.course.Category;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
public class CourseDTO {
    private long id;
    private String name;
    private CourseDetailsDTO courseDetails;
    private CourseFeaturesDTO courseFeatures;
    private Mentor mentor;
    private Category category;
    private List<PriceDetails> priceDetails;
    private String shortDescription;
    private String length;
    private double rate;
    private String image;
    private LocalDateTime createDate;
    private LanguageName language;
}
