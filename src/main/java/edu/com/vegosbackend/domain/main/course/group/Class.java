package edu.com.vegosbackend.domain.main.course.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.com.vegosbackend.domain.main.course.price.PriceDetails;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity(name = "Classes")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Class {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "class_id")
//    private long classId;
//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
//    private Course course;
//    @Column(name = "class_name")
//    private String name;
//    @Column(columnDefinition = "timestamp", name = "start_date")
//    private LocalDateTime startDate;
//    @Column(columnDefinition = "timestamp", name = "end_date")
//    private LocalDateTime endDate;
//    @Column(name = "size")
//    private int size;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "price_id", referencedColumnName = "price_id", nullable = false, columnDefinition = "bigint")
//    private PriceDetails priceDetails;
//
//    @JsonBackReference
//    public Course getCourse() {
//        return course;
//    }
}