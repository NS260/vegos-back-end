package edu.com.vegosbackend.domain.main.course.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "rates")
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Enumerated
    @Column(name = "type", columnDefinition = "smallint", unique = true)
    private RateType type;
    @Column(name = "value")
    private double value;
    @ManyToOne
    @JoinColumn(name = "review_id", columnDefinition = "bigint", referencedColumnName = "id")
    @JsonIgnore
    private Review review;
}
