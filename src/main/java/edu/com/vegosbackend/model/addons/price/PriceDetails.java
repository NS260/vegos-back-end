package edu.com.vegosbackend.model.addons.price;

import edu.com.vegosbackend.model.constants.ClassType;
import edu.com.vegosbackend.model.main.group.Class;
import edu.com.vegosbackend.model.main.course.Course;
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
    @Column(columnDefinition = "smallint", name = "class_type")
    private ClassType classType;
    @Column(name = "price")
    private float price;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    @OneToOne(mappedBy = "priceDetails")
    private Class aClass;

    @Override
    public String toString() {
        return "PriceDetails{" +
                "classType=" + classType +
                ", price=" + price +
                '}';
    }
}
