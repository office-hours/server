package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, UUID> {

  Iterable<Student> getAllByOrderByStudentName();

  Iterable<Student> getAllByStudentNameContainsOrderByStudentNameAsc(String studentName);

  default Student findOrFail(UUID id) {
    return findById(id).get();
  }
}
