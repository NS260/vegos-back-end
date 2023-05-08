package edu.com.vegosbackend.domain.main.user.addons;

import edu.com.vegosbackend.domain.main.user.User;
import edu.com.vegosbackend.domain.main.user.constants.LanguageName;
import edu.com.vegosbackend.domain.main.user.constants.Priority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Languages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private long languageId;
    @Column(name = "language_name",columnDefinition = "smallint")
    @Enumerated
    private LanguageName languageName;
    @Column(name = "priority",columnDefinition = "smallint")
    @Enumerated
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;

}
