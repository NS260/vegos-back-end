package edu.com.vegosbackend.model.main.course;

import edu.com.vegosbackend.model.addons.photo.Photo;
import edu.com.vegosbackend.model.addons.question.QuestionBlock;
import edu.com.vegosbackend.model.addons.review.Review;
import edu.com.vegosbackend.model.addons.structure.CourseStructure;
import edu.com.vegosbackend.model.constants.course.AgeGroup;
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
    @Id
    private long courseDetailsId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id", columnDefinition = "bigint")
    private Course course;
    @Column(name = "video_url")
    private String welcomeVideo;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "age_group")
    private AgeGroup ageGroup;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "course")
    private Set<Review> reviews;
    @OneToMany(mappedBy = "course")
    private Set<QuestionBlock> questionBlocks;
    @OneToMany(mappedBy = "course")
    private Set<Class> classes;
    @OneToMany(mappedBy = "course")
    private Set<Photo> photos;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private CourseStructure courseStructure;
}
