package edu.com.vegosbackend.domain.main.course.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private long photoId;
    @Column(name = "photo_url")
    @NotBlank(message = "Photo url is mandatory")
    private String photoUrl;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    @NotNull(message = "Course cannot be null")
    private Course course;
}
