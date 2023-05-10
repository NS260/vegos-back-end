package edu.com.vegosbackend.domain.main.course.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.com.vegosbackend.domain.constants.course.ReviewRateColumns;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity(name = "rate")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Rate {
   // @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "rate_id")
//    private long rateId;
//    @Enumerated
//    @Column(columnDefinition = "smallint", nullable = false)
//    private ReviewRateColumns reviewRateColumns;
//    @Column(name = "rate")
//    private double rate;
//    @ManyToOne
//    @JoinColumn(name = "review_id", nullable = false, columnDefinition = "bigint")
//    private Review review;
//
//    @JsonBackReference
//    public Review getReview() {
//        return review;
//    }
}
