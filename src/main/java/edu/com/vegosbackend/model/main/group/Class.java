package edu.com.vegosbackend.model.main.group;

import edu.com.vegosbackend.model.addons.price.PriceDetails;
import edu.com.vegosbackend.model.main.course.CourseDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long classId;
    @ManyToOne
    @JoinColumn(name = "course_details_id", nullable = false)
    private CourseDetails courseDetails;
    @Column(name = "class_name")
    private String name;
    @Column(columnDefinition = "timestamp", name = "start_date")
    private LocalDateTime startDate;
    @Column(columnDefinition = "timestamp", name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "size")
    private int size;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", referencedColumnName = "price_id")
    private PriceDetails priceDetails;

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", size=" + size +
                ", priceDetails=" + priceDetails +
                '}';
    }
}