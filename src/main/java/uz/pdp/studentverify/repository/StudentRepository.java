package uz.pdp.studentverify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.studentverify.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean existsByEmail(String email);
    Student findByEmail(String email);
    boolean existsByCode(String code);
    List<Student> getAllByActiveTrue();
}
