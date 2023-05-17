package edu.com.vegosbackend.domain.main.course.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Type of rate cannot be null")
    private RateType type;
    @Column(name = "value")
    private double value;
    @ManyToOne
    @JoinColumn(name = "review_id", columnDefinition = "bigint", referencedColumnName = "id")
    @JsonIgnore
    @NotNull(message = "Review cannot be null")
    private Review review;
}
