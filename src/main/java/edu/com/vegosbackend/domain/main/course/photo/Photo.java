package edu.com.vegosbackend.domain.main.course.photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
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
    private String photoUrl;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Course course;
}
