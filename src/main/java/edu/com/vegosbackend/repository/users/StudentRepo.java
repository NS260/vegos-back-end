package edu.com.vegosbackend.repository.users;

import edu.com.vegosbackend.domain.main.user.roles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student> findStudentsByClassesId(Long id);
}
