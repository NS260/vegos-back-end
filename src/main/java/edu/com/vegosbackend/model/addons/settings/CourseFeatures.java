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
    @Column(name = "course_feature_id")
    private long courseFeatureId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_UID")
    private Course course;

    public CourseFeatures(boolean hasDemoLesson, boolean hasGroupLesson, boolean hasIndividualLesson, boolean hasDayLesson, boolean hasNightLesson) {
        this.hasDemoLesson = hasDemoLesson;
        this.hasGroupLesson = hasGroupLesson;
        this.hasIndividualLesson = hasIndividualLesson;
        this.hasDayLesson = hasDayLesson;
        this.hasNightLesson = hasNightLesson;
    }

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

    @Override
    public String toString() {
        return "CourseFeatures{" +
                "hasDemoLesson=" + hasDemoLesson +
                ", hasGroupLesson=" + hasGroupLesson +
                ", hasIndividualLesson=" + hasIndividualLesson +
                ", hasDayLesson=" + hasDayLesson +
                ", hasNightLesson=" + hasNightLesson +
                ", isActive=" + isActive +
                ", isLocked=" + isLocked +
                ", isClosed=" + isClosed +
                '}';
    }
}
