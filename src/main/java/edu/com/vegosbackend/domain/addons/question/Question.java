package edu.com.vegosbackend.domain.addons.question;

import edu.com.vegosbackend.domain.main.user.roles.Student;
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
    private Student student;
    @Column(columnDefinition = "timestamp", name = "asked_date")
    private LocalDateTime askedDate;
    @OneToOne
    @JoinColumn(name = "question_block_id", nullable = false, columnDefinition = "bigint")
    private QuestionBlock questionBlock;
}
