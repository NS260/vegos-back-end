package edu.com.vegosbackend.domain.main.course;

import edu.com.vegosbackend.domain.addons.photo.Photo;
import edu.com.vegosbackend.domain.addons.question.QuestionBlock;
import edu.com.vegosbackend.domain.addons.review.Review;
import edu.com.vegosbackend.domain.addons.structure.CourseStructure;
import edu.com.vegosbackend.domain.constants.course.AgeGroup;
import edu.com.vegosbackend.domain.main.group.Class;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionBlock> questionBlocks;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseStructure courseStructure;
}
