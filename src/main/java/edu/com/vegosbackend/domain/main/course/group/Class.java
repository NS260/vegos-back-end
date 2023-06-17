package edu.com.vegosbackend.domain.main.course.group;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import edu.com.vegosbackend.domain.constants.course.ClassType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, columnDefinition = "bigint")
    @JsonIgnore
    private Course course;
    @Column(name = "class_name")
    private String name;
    @Column(columnDefinition = "timestamp", name = "start_date")
    private LocalDateTime startDate;
    @Column(columnDefinition = "timestamp", name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "size")
    private int size;
    @ManyToMany(mappedBy = "classes", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Student> studentList;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "class_type", nullable = false)
    @NotNull(message = "Class type value should be specified")
    private ClassType classType;

    public void addStudent(Student student){
        this.getStudentList().add(student);
        student.getClasses().add(this);
    }
}
