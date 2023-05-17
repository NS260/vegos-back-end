package edu.com.vegosbackend.domain.main.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.vegosbackend.domain.constants.global.LanguageName;
import edu.com.vegosbackend.domain.main.course.settings.CourseFeatures;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.domain.constants.course.Category;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Course name cannot be empty")
    private String name;
    @Embedded
    private CourseDetails courseDetails;
    @Embedded
    private CourseFeatures courseFeatures;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint", referencedColumnName = "user_id")
    @NotNull(message = "Mentor cannot be null")
    private Mentor user;
    @Enumerated
    @Column(name = "category_id", nullable = false, columnDefinition = "smallint")
    @NotNull(message = "Category cannot be null")
    private Category category;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceDetails> priceDetails;
    @Column(name = "short_desc")
    @NotEmpty(message = "Short desc cannot be empty")
    private String shortDescription;
    @Column(name = "length")
    @NotEmpty(message = "Length cannot be empty")
    private String length;
    @Column(name = "rate")
    private double rate;
    @Column(name = "image_url")
    @NotBlank(message = "Image Url cannot be empty")
    private String image;
    @Column(columnDefinition = "timestamp", name = "create_date")
    @NotNull(message = "Date cannot be null")
    private LocalDateTime createDate;
    @Enumerated
    @Column(name = "language_id", nullable = false, columnDefinition = "smallint")
    @NotNull(message = "Language value cannot be null")
    private LanguageName language;
    @JsonBackReference
    public Mentor getUser() {
        return user;
    }
    @JsonManagedReference
    public List<PriceDetails> getPriceDetails() {
        return priceDetails;
    }
}
