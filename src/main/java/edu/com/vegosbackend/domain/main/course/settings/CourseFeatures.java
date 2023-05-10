package edu.com.vegosbackend.domain.main.course.settings;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseFeatures {
    @Column(name = "has_demo_lesson", columnDefinition = "boolean")
    private boolean hasDemoLesson;
    @Column(name = "has_group_lesson", columnDefinition = "boolean")
    private boolean hasGroupLesson;
    @Column(name = "has_individ_lesson", columnDefinition = "boolean")
    private boolean hasIndividualLesson;
    @Column(name = "has_day_lesson", columnDefinition = "boolean")
    private boolean hasDayLesson;
    @Column(name = "has_night_lesson", columnDefinition = "boolean")
    private boolean hasNightLesson;
    @Column(name = "is_active", columnDefinition = "boolean")
    private boolean isActive;
    @Column(name = "is_locked", columnDefinition = "boolean")
    private boolean isLocked;
    @Column(name = "is_closed", columnDefinition = "boolean")
    private boolean isClosed;
}
