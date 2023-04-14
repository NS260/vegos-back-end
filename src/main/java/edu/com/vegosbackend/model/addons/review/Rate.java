package edu.com.vegosbackend.model.addons.review;

import edu.com.vegosbackend.model.addons.constants.ReviewRateColumns;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private long rateId;
    @Enumerated
    @Column(columnDefinition = "smallint")
    private ReviewRateColumns reviewRateColumns;
    @Column(name = "rate")
    private double rate;
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Override
    public String toString() {
        return "Rate{" +
                ", reviewRateColumns=" + reviewRateColumns +
                ", rate=" + rate +
                '}';
    }
}
