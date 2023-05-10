package edu.com.vegosbackend.domain.main.course.question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity(name = "question_block")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class QuestionBlock {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "question_block_id")
//    private long questionBlockId;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionBlock")
//    private Answer answer;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionBlock")
//    private Question question;
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
//    private Course course;
//
//    @JsonBackReference
//    public Course getCourse() {
//        return course;
//    }
}
