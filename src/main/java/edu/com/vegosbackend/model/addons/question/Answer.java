package edu.com.vegosbackend.model.addons.question;

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
    @Column(name = "author_id")
    private String author;//TODO class as definition of Author entity
    @Column(columnDefinition = "timestamp", name = "answered_date")
    private LocalDateTime answeredDate;
    @OneToOne
    @MapsId
    @JoinColumn(name = "question_block_id")
    private QuestionBlock questionBlock;

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", answeredDate=" + answeredDate +
                '}';
    }
}
