package edu.com.vegosbackend.model.addons.question;

import edu.com.vegosbackend.model.main.user.User;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    @Column(columnDefinition = "timestamp", name = "asked_date")
    private LocalDateTime askedDate;
    @OneToOne
    @JoinColumn(name = "question_block_id")
    private QuestionBlock questionBlock;

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", askedDate=" + askedDate +
                '}';
    }
}
