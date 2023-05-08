package edu.com.vegosbackend.domain.main.user.roles;

import edu.com.vegosbackend.domain.addons.question.Question;
import edu.com.vegosbackend.domain.addons.review.Review;
import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Role{
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Question> question;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Review> review;

    public Student(User user) {
        this.user=user;
    }
}