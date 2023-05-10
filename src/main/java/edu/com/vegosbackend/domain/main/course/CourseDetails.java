package edu.com.vegosbackend.domain.main.course;

import edu.com.vegosbackend.domain.main.course.photo.Photo;
import edu.com.vegosbackend.domain.main.course.question.QuestionBlock;
import edu.com.vegosbackend.domain.main.course.review.Review;
import edu.com.vegosbackend.domain.main.course.structure.CourseStructure;
import edu.com.vegosbackend.domain.constants.course.AgeGroup;
import edu.com.vegosbackend.domain.main.course.group.Class;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {
    @Column(name = "video_url")
    private String welcomeVideo;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "age_group")
    private AgeGroup ageGroup;
    @Column(name = "description")
    private String description;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Review> reviews;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<QuestionBlock> questionBlocks;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Class> classes;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;
    @Embedded
    private CourseStructure courseStructure;

//    @JsonManagedReference
//    public List<Class> getClasses() {
//        return classes;
//    }

//    @JsonManagedReference
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    @JsonManagedReference
//    public List<QuestionBlock> getQuestionBlocks() {
//        return questionBlocks;
//    }
}
