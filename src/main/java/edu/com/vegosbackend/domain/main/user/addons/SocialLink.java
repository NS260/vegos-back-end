package edu.com.vegosbackend.domain.main.user.addons;

import edu.com.vegosbackend.domain.main.user.User;
import edu.com.vegosbackend.domain.main.user.constants.SocialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Social Links")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(columnDefinition = "smallint", name = "social_type")
    @Enumerated
    private SocialType socialType;
    @Column(name = "link")
    private String link;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;
}
