package edu.com.vegosbackend.model.main.user.addons;

import edu.com.vegosbackend.model.main.user.UserDetails;
import edu.com.vegosbackend.model.main.user.constants.SocialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "social_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_id")
    private long socialId;
    @Column(columnDefinition = "smallint", name = "social_type")
    @Enumerated
    private SocialType socialType;
    @Column(name = "link")
    private String link;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint")
    private UserDetails user;
}
