package edu.com.vegosbackend.model.main.user.addons;

import edu.com.vegosbackend.model.main.user.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "education")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private long educationId;
    @Column(name = "institution")
    private String institution;
    @Column(columnDefinition = "timestamp", name = "start_date")
    private LocalDateTime start;
    @Column(columnDefinition = "timestamp", name = "end_date")
    private LocalDateTime end;
    @Column(name = "degree")
    private String degree;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private UserDetails user;
}
