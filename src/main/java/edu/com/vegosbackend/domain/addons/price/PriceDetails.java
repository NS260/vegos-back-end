package edu.com.vegosbackend.domain.addons.price;

import edu.com.vegosbackend.domain.constants.course.ClassType;
import edu.com.vegosbackend.domain.main.group.Class;
import edu.com.vegosbackend.domain.main.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "price_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private long priceId;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "class_type", nullable = false)
    private ClassType classType;
    @Column(name = "price")
    private float price;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    private Course course;
    @OneToOne(mappedBy = "priceDetails")
    private Class aClass;
}
