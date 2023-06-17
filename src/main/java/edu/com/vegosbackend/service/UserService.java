package edu.com.vegosbackend.service;

import edu.com.vegosbackend.domain.main.user.constants.UserRole;
import edu.com.vegosbackend.domain.main.user.User;
import edu.com.vegosbackend.domain.main.user.UserDetails;
import edu.com.vegosbackend.domain.constants.global.LanguageName;
import edu.com.vegosbackend.domain.main.user.roles.Admin;
import edu.com.vegosbackend.domain.main.user.roles.Mentor;
import edu.com.vegosbackend.domain.main.user.roles.Student;
import edu.com.vegosbackend.repository.users.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Optional<User> createUser(User user) {
        addRoleEntityToCustomTable(user);
        return Optional.of(Optional.of(
                        userRepo.save(user))
                .orElseThrow());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.of(userRepo
                .findById(id)
                .orElseThrow());
    }

    public Optional<User> updateUserById(User user, Long id) {
        userRepo.delete(userRepo
                .findById(id)
                .orElseThrow());
        return Optional.of(Optional.of(userRepo
                        .save(user))
                .orElseThrow());
    }

    public Optional<Boolean> deleteUserById(Long id) {
        userRepo.deleteById(id);
        return Optional.of(Optional.of(userRepo
                        .existsById(id))
                .orElseThrow());
    }

    public List<User> getAllUsers() {
        return Optional.of(userRepo
                        .findAll())
                .orElseThrow();
    }

    public List<User> getAllUsersByAge(int age) {
        return Optional.of(userRepo
                        .findUsersByAge(age))
                .orElseThrow();
    }

    public List<User> getAllUsersInAgeRange(int min, int max) {
        return Optional.of(userRepo
                        .findUsersByAgeBetween(min, max))
                .orElseThrow();
    }

    public List<User> getAllUsersWithNameOrSurname(String name, String surname) {
        return Optional.of(userRepo
                        .findUsersByNameOrSurname(name, surname))
                .orElseThrow();
    }

    public List<User> getAllUsersByLanguage(LanguageName languageName) {
        return Optional.of(userRepo
                        .findUsersByLanguage(languageName))
                .orElseThrow();
    }

    public List<User> getAllUsersByRole(UserRole userRole) {
        return Optional.of(userRepo
                        .findUsersByUserRole(userRole))
                .orElseThrow();
    }

    public void clear() {
        userRepo.deleteAll();
    }

    private void addRoleEntityToCustomTable(User user) {
        user.setUser(new UserDetails());
        if (user.getUserRole().equals(UserRole.Admin)) {
            user.getUser().setAdmin(new Admin(user));
        }
        if (user.getUserRole().equals(UserRole.Mentor)) {
            user.getUser().setMentor(new Mentor(user));
        }
        if (user.getUserRole().equals(UserRole.Student)) {
            user.getUser().setStudent(new Student(user));
        }
    }
}
