package edu.com.vegosbackend.domain.main.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.vegosbackend.domain.constants.global.LanguageName;
import edu.com.vegosbackend.domain.main.course.settings.CourseFeatures;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
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
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Embedded
    private CourseDetails courseDetails;
    @Embedded
    private CourseFeatures courseFeatures;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint", referencedColumnName = "id")
    private User user;
    @Enumerated
    @Column(name = "category_id", nullable = false, columnDefinition = "smallint")
    private Category category;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceDetails> priceDetails;
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
    @Enumerated
    @Column(name = "language_id", nullable = false, columnDefinition = "smallint")
    private LanguageName language;
    @JsonBackReference
    public User getUser() {
        return user;
    }
    @JsonManagedReference
    public List<PriceDetails> getPriceDetails() {
        return priceDetails;
    }
}
