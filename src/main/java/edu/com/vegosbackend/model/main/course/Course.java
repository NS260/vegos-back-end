package edu.com.vegosbackend.model.main.course;

import edu.com.vegosbackend.model.addons.settings.CourseFeatures;
import edu.com.vegosbackend.model.addons.price.PriceDetails;
import edu.com.vegosbackend.model.constants.course.Category;
import edu.com.vegosbackend.model.main.user.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;
    @Column(name = "course_name")
    private String name;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private CourseDetails courseDetails;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private CourseFeatures courseFeatures;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private UserDetails user;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "category", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "course")
    private Set<PriceDetails> priceDetailsSet;
    @Column(name = "short_desc")
    private String shortDescription;
    @Column(name = "length")
    private String length;
    @Column(name = "rate")
    private double rate;
    @Column(name = "image_url")
    private String image;
    @Column(columnDefinition = "timestamp", name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "language")
    private String language;
}
