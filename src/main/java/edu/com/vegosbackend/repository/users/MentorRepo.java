package edu.com.vegosbackend.repository.users;

import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepo extends JpaRepository<Mentor, Long> {
}
