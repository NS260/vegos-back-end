package edu.com.vegosbackend.model.addons.review;

import edu.com.vegosbackend.model.main.course.CourseDetails;
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
    @Column(name = "author_id")
    private String author;
    @Column(name = "text")
    private String text;
    @OneToMany(mappedBy = "review")
    private Set<Rate> rates;
    @ManyToOne
    @JoinColumn(name = "course_details_id", nullable = false)
    private CourseDetails courseDetails;

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", rates=" + rates +
                '}';
    }
}
