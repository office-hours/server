package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Database operations that can be performed on {@link Teacher} entity instances.
 */
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

  /**
   * Selects and returns all {@link Teacher} instances pertaining to and ordered by student names.
   *
   * @return {@link Iterable} sequence of {@link Teacher} instances.
   */
  Iterable<Teacher> getAllByOrderByTeacherName();

  /**
   * Defaults to finding a {@link Teacher} id or failing.
   *
   * @param id for {@link Teacher}.
   * @return a {@link Teacher} id.
   */
  default Teacher findOrFail(UUID id) {
    return findById(id).get();
  }


}
