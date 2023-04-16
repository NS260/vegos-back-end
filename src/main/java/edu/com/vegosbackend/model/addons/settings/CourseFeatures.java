package edu.com.vegosbackend.model.addons.settings;

import edu.com.vegosbackend.model.main.course.Course;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "course_features")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseFeatures {
    @Id
    private long courseFeatureId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id", nullable = false,columnDefinition = "bigint")
    private Course course;
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
