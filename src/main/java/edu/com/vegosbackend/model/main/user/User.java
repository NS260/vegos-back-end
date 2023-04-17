package edu.com.vegosbackend.model.main.user;

import edu.com.vegosbackend.model.constants.user.UserRole;
import edu.com.vegosbackend.model.main.user.constants.LanguageName;
import edu.com.vegosbackend.model.main.user.roles.Admin;
import edu.com.vegosbackend.model.main.user.roles.Mentor;
import edu.com.vegosbackend.model.main.user.roles.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String mobilePhone;
    @Enumerated
    @Column(name = "language", columnDefinition = "smallint")
    private LanguageName language;
    @Column(name = "age")
    private int age;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "role", nullable = false)
    private UserRole userRole;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails user;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Mentor mentor;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Student student;

}
