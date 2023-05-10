package edu.com.vegosbackend.domain.main.course.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@Entity(name = "review")
//@Data
public class Review {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "review_id")
//    private long reviewId;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
//    private Student student;
//    @Column(name = "text")
//    private String text;
//    @OneToMany(mappedBy = "review")
//    private List<Rate> rates;
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
//    private Course course;
//
//    @JsonManagedReference
//    public List<Rate> getRates() {
//        return rates;
//    }
//
//    @JsonBackReference
//    public Student getStudent() {
//        return student;
//    }
//
//    @JsonBackReference
//    public Course getCourse() {
//        return course;
//    }
}
