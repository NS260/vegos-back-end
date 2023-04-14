package edu.com.vegosbackend.model.main.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    @Column(name="user_details_id")
    private long userDetailsId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
