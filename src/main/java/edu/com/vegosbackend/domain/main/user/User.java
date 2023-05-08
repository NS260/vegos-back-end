package edu.com.vegosbackend.domain.main.user;

import edu.com.vegosbackend.domain.constants.user.UserRole;
import edu.com.vegosbackend.domain.main.user.constants.LanguageName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
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
    @Column(name = "status")
    private String status;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "info")
    private String info;
    @Column(name = "image_url")
    private String imageUrl;
    @Enumerated
    @Column(name = "language", columnDefinition = "smallint")
    private LanguageName language;
    @Column(name = "age")
    private int age;
    @Enumerated
    @Column(columnDefinition = "smallint", name = "role", nullable = false)
    private UserRole userRole;
    @Embedded
    private UserDetails user;

}
