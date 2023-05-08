package edu.com.vegosbackend.domain.main.course;

import edu.com.vegosbackend.domain.addons.settings.CourseFeatures;
import edu.com.vegosbackend.domain.addons.price.PriceDetails;
import edu.com.vegosbackend.domain.constants.course.Category;
import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "courses")
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
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseDetails courseDetails;
    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private CourseFeatures courseFeatures;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;
    @Enumerated
    @Column(name = "category_id", nullable = false, columnDefinition = "smallint")
    private Category category;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceDetails> priceDetailsSet;
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
