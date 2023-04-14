package edu.com.vegosbackend.model.addons.question;

import edu.com.vegosbackend.model.main.course.CourseDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "question_block")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_block_id")
    private long questionBlockId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionBlock")
    private Answer answer;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionBlock")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "course_details_id", nullable = false)
    private CourseDetails courseDetails;

    @Override
    public String toString() {
        return "QuestionBlock{" +
                "answer=" + answer +
                ", question=" + question +
                '}';
    }
}
