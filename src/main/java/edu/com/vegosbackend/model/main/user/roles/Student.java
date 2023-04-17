package edu.com.vegosbackend.model.main.user.roles;

import edu.com.vegosbackend.model.addons.question.Question;
import edu.com.vegosbackend.model.addons.review.Review;
import edu.com.vegosbackend.model.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private User user;

    @OneToMany(mappedBy = "student")
    private Set<Question> question;
    @OneToMany(mappedBy = "student")
    private Set<Review> review;

}
