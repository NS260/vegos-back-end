package edu.com.vegosbackend.domain.main.user.addons;

import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Educations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "institution")
    private String institution;
    @Column(columnDefinition = "timestamp", name = "start_date")
    private LocalDateTime start;
    @Column(columnDefinition = "timestamp", name = "end_date")
    private LocalDateTime end;
    @Column(name = "degree")
    private String degree;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;
}
