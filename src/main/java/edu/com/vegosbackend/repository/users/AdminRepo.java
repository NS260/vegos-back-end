package edu.com.vegosbackend.repository.users;

import edu.com.vegosbackend.domain.main.user.roles.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
}
