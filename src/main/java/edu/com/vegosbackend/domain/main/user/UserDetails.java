package edu.com.vegosbackend.domain.main.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.com.vegosbackend.domain.main.article.Article;
import edu.com.vegosbackend.domain.main.course.Course;
import edu.com.vegosbackend.domain.main.user.addons.Education;
import edu.com.vegosbackend.domain.main.user.addons.Language;
import edu.com.vegosbackend.domain.main.user.addons.SocialLink;
import edu.com.vegosbackend.domain.main.user.roles.Admin;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<Article> articles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Education> educations;
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SocialLink> socialLinks;
    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Language> languages;
    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Admin admin;
    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Mentor mentor;
    @JsonIgnore
    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Student student;
}
