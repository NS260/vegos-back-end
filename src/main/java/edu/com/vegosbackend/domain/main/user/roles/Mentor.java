package edu.com.vegosbackend.domain.main.user.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.com.vegosbackend.domain.main.course.Course;
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
public class Mentor implements Role {
    @Id
    private long id;
    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answer> answer;

    @OneToMany(mappedBy = "mentor",cascade = CascadeType.MERGE)
    private List<Course> course;

    @JsonManagedReference
    public List<Course> getCourse() {
        return course;
    }

    public Mentor(User user) {
        this.user = user;
    }
}
