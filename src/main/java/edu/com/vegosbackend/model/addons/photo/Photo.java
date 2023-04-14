package edu.com.vegosbackend.model.addons.photo;

import edu.com.vegosbackend.model.main.course.CourseDetails;
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
    @JoinColumn(name = "course_details_id", nullable = false)
    private CourseDetails courseDetails;

    @Override
    public String toString() {
        return "Photo{" +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
