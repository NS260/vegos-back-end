package edu.com.vegosbackend.model.main.user;

import edu.com.vegosbackend.model.constants.course.Category;
import edu.com.vegosbackend.model.main.article.Article;
import edu.com.vegosbackend.model.main.course.Course;
import edu.com.vegosbackend.model.main.user.addons.Education;
import edu.com.vegosbackend.model.main.user.addons.Language;
import edu.com.vegosbackend.model.main.user.addons.SocialLink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    private long userDetailsId;
    @Column(name = "status")
    private String status;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "info")
    private String info;
    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private User user;
    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "user_details", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "category_id", columnDefinition = "smallint")
    @Enumerated
    private Set<Category> categories;

    @OneToMany(mappedBy = "user")
    private Set<Article> articles;
    @OneToMany(mappedBy = "user")
    private Set<Course> course;
    @OneToMany(mappedBy = "user")
    private Set<Education> educations;
    @OneToMany(mappedBy = "user")
    private Set<SocialLink> socialLinks;
    @OneToMany(mappedBy = "user")
    private Set<Language> languages;
}
