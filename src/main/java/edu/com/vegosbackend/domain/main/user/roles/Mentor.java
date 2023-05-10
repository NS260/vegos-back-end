package edu.com.vegosbackend.domain.main.user.roles;

import edu.com.vegosbackend.domain.main.course.question.Answer;
import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Mentors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentor implements Role{
    @Id
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;
//    @OneToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
//    private List<Answer> answer;

    public Mentor(User user) {
        this.user = user;
    }
}
