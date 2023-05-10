package edu.com.vegosbackend.controller.settings.model.dto.course.details;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CourseFeaturesDTO {
    private boolean hasDemoLesson;
    private boolean hasGroupLesson;
    private boolean hasIndividualLesson;
    private boolean hasDayLesson;
    private boolean hasNightLesson;
    private boolean isActive;
    private boolean isLocked;
    private boolean isClosed;
}
