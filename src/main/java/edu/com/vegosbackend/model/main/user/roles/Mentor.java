package edu.com.vegosbackend.model.main.user.roles;

import edu.com.vegosbackend.model.addons.question.Answer;
import edu.com.vegosbackend.model.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "mentor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {
    @Id
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private User user;
    @OneToMany(mappedBy = "mentor")
    private Set<Answer> answer;
}
