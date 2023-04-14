package edu.com.vegosbackend.model.addons.question;

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
    @Column(name = "author_id")
    private String author; //TODO class as definition of Author entity
    @Column(columnDefinition = "timestamp", name = "asked_date")
    private LocalDateTime askedDate;
    @OneToOne
    @MapsId
    @JoinColumn(name = "question_block_id")
    private QuestionBlock questionBlock;

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", askedDate=" + askedDate +
                '}';
    }
}
