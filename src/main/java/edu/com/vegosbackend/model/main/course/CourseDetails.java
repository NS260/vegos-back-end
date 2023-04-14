package edu.com.vegosbackend.model.main.course;

import edu.com.vegosbackend.model.addons.photo.Photo;
import edu.com.vegosbackend.model.addons.question.QuestionBlock;
import edu.com.vegosbackend.model.addons.review.Review;
import edu.com.vegosbackend.model.addons.structure.CourseStructure;
import edu.com.vegosbackend.model.constants.AgeGroup;
import edu.com.vegosbackend.model.main.group.Class;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "course_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {
    public CourseDetails(String welcomeVideo, AgeGroup ageGroup, String description, Set<Review> reviews, Set<QuestionBlock> questionBlocks, Set<Class> classes, Set<Photo> photos, CourseStructure courseStructure) {
        this.welcomeVideo = welcomeVideo;
        this.ageGroup = ageGroup;
        this.description = description;
        this.reviews = reviews;
        this.questionBlocks = questionBlocks;
        this.classes = classes;
        this.photos = photos;
        this.courseStructure = courseStructure;
    }

    @Id
    @Column(name = "course_details_id")
    private long courseDetailsId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "video_url")
    private String welcomeVideo;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "age_group")
    private AgeGroup ageGroup;
    @Column(name = "desc")
    private String description;
    @OneToMany(mappedBy = "course_details")
    private Set<Review> reviews;
    @OneToMany(mappedBy = "course_details")
    private Set<QuestionBlock> questionBlocks;
    @OneToMany(mappedBy = "course_details")
    private Set<Class> classes;
    @OneToMany(mappedBy = "course_details")
    private Set<Photo> photos;
    @OneToOne(mappedBy = "course_details", cascade = CascadeType.ALL)
    private CourseStructure courseStructure;

    @Override
    public String toString() {
        return "CourseDetails{" +
                "welcomeVideo='" + welcomeVideo + '\'' +
                ", ageGroup=" + ageGroup +
                ", description='" + description + '\'' +
                ", reviews='" + reviews + '\'' +
                ", questions='" + questionBlocks + '\'' +
                ", classes='" + classes + '\'' +
                ", photos='" + photos + '\'' +
                ", structure='" + courseStructure + '\'' +
                '}';
    }
}
