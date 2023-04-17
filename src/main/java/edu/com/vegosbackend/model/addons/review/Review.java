package edu.com.vegosbackend.model.addons.review;

import edu.com.vegosbackend.model.main.course.CourseDetails;
import edu.com.vegosbackend.model.main.user.roles.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private Student student;
    @Column(name = "text")
    private String text;
    @OneToMany(mappedBy = "review")
    private Set<Rate> rates;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    private CourseDetails course;
}
