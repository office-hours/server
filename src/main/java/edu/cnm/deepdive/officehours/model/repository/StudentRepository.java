package edu.cnm.deepdive.officehours.model.repository;

import edu.cnm.deepdive.officehours.model.entity.Student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database operations that can be performed on {@link Student} entity instances.
 */
public interface StudentRepository extends JpaRepository<Student, UUID> {

  /**
   * Selects and returns all {@link Student} instances pertaining to and ordered by student names.
   *
   * @return {@link Iterable} sequence of {@link Student} instances.
   */
  Iterable<Student> getAllByOrderByStudentName();

  Student findFirstByStudentName(String studentName);

  /**
   * Defaults to finding a {@link Student} id or failing.
   *
   * @param id for {@link Student}.
   * @return a {@link Student} id.
   */
  default Student findOrFail(UUID id) {
    return findById(id).get();
  }
}
