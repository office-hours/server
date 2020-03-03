package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Teacher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

  Iterable<Teacher> getAllByOrderByTeacherName();

  Iterable<Teacher> getAllByTeacherNameContainsOrderByTeacherNameAsc(String fragment);

  default Teacher findOrFail(UUID id) {
    return findById(id).get();
  }



}
