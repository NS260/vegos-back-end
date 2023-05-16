package edu.com.vegosbackend.domain.main.course.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Mentor mentor;
    @Column(columnDefinition = "timestamp", name = "answered_date")
    private LocalDateTime answeredDate;
    @OneToOne(mappedBy = "answer")
    @JsonIgnore
    private Question question;
}
