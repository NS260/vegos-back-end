package edu.com.vegosbackend.domain.main.course.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id",referencedColumnName = "id")
    @JsonIgnore
    private Answer answer;
    @Column(columnDefinition = "timestamp", name = "asked_date")
    private LocalDateTime askedDate;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Course course;
}
