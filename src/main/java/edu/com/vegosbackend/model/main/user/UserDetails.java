package edu.com.vegosbackend.model.main.user;

import edu.com.vegosbackend.model.addons.question.Answer;
import edu.com.vegosbackend.model.addons.question.Question;
import edu.com.vegosbackend.model.addons.review.Review;
import edu.com.vegosbackend.model.main.article.Article;
import edu.com.vegosbackend.model.main.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails { //TODO fill the entity to store data about User profile details
    @Id
    private long userDetailsId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private User user;
    @OneToMany(mappedBy = "user")
    private Set<Article> articles;
    @OneToMany(mappedBy = "user")
    private Set<Course> course;
    @OneToMany(mappedBy = "user")
    private Set<Answer> answer;
    @OneToMany(mappedBy = "user")
    private Set<Question> question;
    @OneToMany(mappedBy = "user")
    private Set<Review> review;
}
