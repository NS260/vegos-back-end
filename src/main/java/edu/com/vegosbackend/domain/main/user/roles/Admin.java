package edu.com.vegosbackend.domain.main.user.roles;

import edu.com.vegosbackend.domain.main.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Role {
    @Id
    private long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "bigint",referencedColumnName = "id")
    private User user;

    public Admin(User user) {
        this.user = user;
    }
}
