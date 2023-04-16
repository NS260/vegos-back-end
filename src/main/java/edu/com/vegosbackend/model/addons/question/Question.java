package edu.com.vegosbackend.model.addons.question;

import edu.com.vegosbackend.model.main.user.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long questionId;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private UserDetails user;
    @Column(columnDefinition = "timestamp", name = "asked_date")
    private LocalDateTime askedDate;
    @OneToOne
    @JoinColumn(name = "question_block_id", nullable = false, columnDefinition = "bigint")
    private QuestionBlock questionBlock;
}
