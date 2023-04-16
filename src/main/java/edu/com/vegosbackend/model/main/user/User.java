package edu.com.vegosbackend.model.main.user;

import edu.com.vegosbackend.model.constants.user.UserRole;
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
    @Column(name = "basic_language")
    private String primaryLanguage;
    @Column(name = "age")
    private int age;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "role", nullable = false)
    private UserRole userRole;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;
}
