package edu.com.vegosbackend.model.addons.question;

import edu.com.vegosbackend.model.main.user.User;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    @Column(columnDefinition = "timestamp", name = "answered_date")
    private LocalDateTime answeredDate;
    @OneToOne
    @JoinColumn(name = "question_block_id")
    private QuestionBlock questionBlock;

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", answeredDate=" + answeredDate +
                '}';
    }
}
