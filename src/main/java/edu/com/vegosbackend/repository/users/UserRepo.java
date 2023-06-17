package edu.com.vegosbackend.repository.users;

import edu.com.vegosbackend.domain.main.user.constants.UserRole;
import edu.com.vegosbackend.domain.main.user.User;
import edu.com.vegosbackend.domain.constants.global.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findUsersByAge(Integer age);
    List<User> findUsersByAgeBetween(Integer min,Integer max);
    List<User> findUsersByNameOrSurname(String name, String surname);
    List<User> findUsersByLanguage(LanguageName languageName);
    List<User> findUsersByUserRole(UserRole role);
}
