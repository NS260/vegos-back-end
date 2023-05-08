package edu.com.vegosbackend.domain.addons.question;

import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private Mentor mentor;
    @Column(columnDefinition = "timestamp", name = "answered_date")
    private LocalDateTime answeredDate;
    @OneToOne
    @JoinColumn(name = "question_block_id", nullable = false, columnDefinition = "bigint")
    private QuestionBlock questionBlock;
}